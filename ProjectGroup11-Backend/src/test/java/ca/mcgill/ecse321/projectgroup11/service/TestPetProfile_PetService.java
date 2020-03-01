package ca.mcgill.ecse321.projectgroup11.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse321.projectgroup11.dao.PetProfileRepository;
import ca.mcgill.ecse321.projectgroup11.dao.PetRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;


@ExtendWith(MockitoExtension.class)
public class TestPetProfile_PetService {
	@Mock
	private PetProfileRepository petProfileDao;
	private PetRepository petDao;

	@InjectMocks
	private PetService service;

	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;

	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(petProfileDao.findPetProfileById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_KEY)) {
				Shelter shelter = new Shelter();
				shelter.setId(USER_KEY);
				return shelter;
			} else {
				return null;
			}
		});	
	}
	
	@Test
	public void testCreatePetProfile() {
		assertEquals(0, service.getAllPetProfiles().size());
		
		Integer ID = 66;
		String name = "Bobo";
		String type = "cat";
		String description = "chubby";
		String photoURL = "urlurl";
		String breed = "street cat";
		Boolean apartment = true;
		Boolean kidOk = true;
		Boolean petOk = true;
		Boolean highE = false;
		String health = "chubby but healthy boi";
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description, photoURL, breed, apartment, kidOk, petOk, highE, health);
		}	catch (Exception e) {
			fail();
		}
		assertNotNull(petProfile);
		assertEquals(ID, petProfile.getId());
		assertEquals(name, petProfile.getName());
		assertEquals(type, petProfile.getType());
		assertEquals(description, petProfile.getDescription());
		assertEquals(photoURL, petProfile.getPhotoURL());
		assertEquals(breed, petProfile.getBreed());
		assertEquals(apartment, petProfile.getApartment());
		assertEquals(kidOk, petProfile.getKidsOkay());
		assertEquals(petOk, petProfile.getPetsOkay());
		assertEquals(highE, petProfile.getHighEnergy());
		assertEquals(health, petProfile.getHealthConcerns());
	}
	
	@Test
	public void testCreatePetProfileNull() {
		Integer ID = null;
		String name = null;
		String type = null;
		String description = null;
		String photoURL = null;
		String breed = null;
		Boolean apartment = null;
		Boolean kidOk = null;
		Boolean petOk = null;
		Boolean highE = null;
		String health = null;
		String error = null;
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description, photoURL, breed, apartment, kidOk, petOk, highE, health);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(petProfile);
		//To correct
		assertEquals("PetProfile cannot be empty!", error);
		
	}
	
	@Test
	public void testCreatePetProfileEmpty() {
		Integer ID = null;
		String name = "";
		String type = "";
		String description = "";
		String photoURL = "";
		String breed = "";
		Boolean apartment = null;
		Boolean kidOk = null;
		Boolean petOk = null;
		Boolean highE = null;
		String health = "";
		String error = null;
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description, photoURL, breed, apartment, kidOk, petOk, highE, health);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(petProfile);
		//To correct
		assertEquals("PetProfile cannot be empty!", error);
		
	}
	
	@Test
	public void testCreatePetProfileSpaces() {
		Integer ID = null;
		String name = " ";
		String type = " ";
		String description = " ";
		String photoURL = " ";
		String breed = " ";
		Boolean apartment = null;
		Boolean kidOk = null;
		Boolean petOk = null;
		Boolean highE = null;
		String health = " ";
		String error = null;
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description, photoURL, breed, apartment, kidOk, petOk, highE, health);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(petProfile);
		//To correct
		assertEquals("PetProfile cannot be empty!", error);
		
	}
	
	
	
	/*@Transactional
	public PetProfile creatPetProfile(Integer ID, String name, 
									  String type, String description, 
								      String photoURL, String breed, Boolean apartment, 
									  Boolean kidOk, Boolean petOk, Boolean highE, String health) {
		if (ID == null &&
			(name == null || name == "") &&
			(type == null || type == "") &&
			(description == null || description == "") &&
			(photoURL == null || photoURL == "") &&
			(breed == null || breed == "") &&
			apartment == null &&
			kidOk == null &&
			petOk == null &&
			highE == null &&
			(health == null || health == "")) {
			throw new IllegalArgumentException("PetProfile cannot be empty!");
		}
		//To complete PetProfile petProfile = petProfileRepository.findpetProfileByID(ID);
			
	}*/	
	
}