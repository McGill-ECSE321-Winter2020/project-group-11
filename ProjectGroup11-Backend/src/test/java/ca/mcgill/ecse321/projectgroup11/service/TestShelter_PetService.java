package ca.mcgill.ecse321.projectgroup11.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse321.projectgroup11.dao.AccountUserRepository;
import ca.mcgill.ecse321.projectgroup11.dao.ShelterRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;
import ca.mcgill.ecse321.projectgroup11.service.PetService;

@ExtendWith(MockitoExtension.class)
class TestShelter_PetService{
	
	@Mock
	private ShelterRepository shelterDao;
	private AccountUserRepository managerDao;

	@InjectMocks
	private PetService service;

	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;

	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(shelterDao.findShelterById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_KEY)) {
				Shelter shelter = new Shelter();
				shelter.setId(USER_KEY);
				return shelter;
			} else {
				return null;
			}
		});
		
	

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(shelterDao.save(any(Shelter.class))).thenAnswer(returnParameterAsAnswer);
		
		

		
	}
	
	@Test
	// Try creating Shelter with the same ID
	void testCreateShelterWithSameId() {
		Shelter s = null;
		try {
			s = service.createShelter(USER_KEY);

		}
		catch(Exception e) {
			assertNull(s);
			assertEquals(e.getMessage(), "Shelter with this ID already exists");
		}
		
	}
	
	@Test
	// Try creating Shelter with null elements for manager and pets
	void testCreateShelterWithNullElements() {
		Shelter s = service.createShelter(50, null, null);
		assertNotNull(s);
		assertEquals(s.getId() , 50);
		assertEquals(s.getManager(), null);
		assertEquals(s.getPet(), null);
	}
	@Test
	// Try creating Shelter with empty list of pets
	void testCreateShelterWithEmptyListOfPets() {
		List<Pet> a = new ArrayList<Pet>();
		Shelter shelter = null;

		
		try {
		 shelter = service.createShelter(50, a, null);
		}
		catch(Exception e) {
			assertNull(shelter);
			assertEquals(e.getMessage(), "Please do not create an empty set of pets");
		}
		
		
	}
	
	@Test
	// Try creating shelter with a manager not saved
	void testCreateShelterWithManagerNotSaved() {
		Manager m = new Manager();
		Shelter shelter = null;
		m.setUserID(50);
	
		try {
			 shelter = service.createShelter(50, null, m);
		
		}
		catch(Exception e) {
			
			assertEquals(e.getMessage() , "Manager must be saved first");
			assertNull(shelter);
			
		}
	}
	
	@Test
	// Try updating a Shelter that is not saved in the dB
	void testUpdateShelter() {
		Shelter s = new Shelter();
		try {
			service.updateShelter(s);
		}
		catch (Exception e) {
			assertEquals(e.getMessage() , "Cannot update shelter that is not in the database");
		}
		
		
	}
	@Test
	// Try to get an null Shelter by browsing for nonexisting iD
	public void testGetNonExistingShelter() {
		assertNull(service.getShelterById(NONEXISTING_KEY));
	}
	
	@Test
	// Try to get an null Shelter by browsing for nonexisting iD
	public void testGetExistingShelter() {
		assertEquals(USER_KEY, service.getShelterById(USER_KEY).getId());
	}
	
	
	
	
	
}