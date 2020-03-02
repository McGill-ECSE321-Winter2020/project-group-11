package ca.mcgill.ecse321.projectgroup11.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup11.dao.AdoptionPostingRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.service.PetService;


/**
 *
 *@author ProjectGroup11
 *Tests the AdoptionPosting methods in PetService
 *
 */

@ExtendWith(MockitoExtension.class)
public class TestAdoptionPosting_PetService {
	@Mock
	private AdoptionPostingRepository adoptionPostingDao;

	@InjectMocks
	private PetService service;


	public static final Integer USER_ID = 5;
	public static final Pet USER_PET = new Pet();	
	public static final Set<Adopter> USER_ADOPTER_SET = new HashSet<Adopter>();
	public static final List<Adopter> USER_ADOPTER_LIST = new ArrayList<Adopter>();
	
	public static final Integer NONEXISTING_ID = 20;
	public static final Pet NONEXISTING_PET = new Pet();	
	public static final Set<Adopter> NONEXISITNG_ADOPTER_SET = new HashSet<Adopter>();
	public static final List<Adopter> NONEXISTING_ADOPTER_LIST = new ArrayList<Adopter>();

	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(adoptionPostingDao.findAdoptionPostingById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_ID)) {
				AdoptionPosting adoptionPosting = new AdoptionPosting();
				adoptionPosting.setId(USER_ID);
				adoptionPosting.setPet(USER_PET);
				adoptionPosting.setAdopters(USER_ADOPTER_SET);

				return adoptionPosting;
			} else {
				return null;
			}
		});	

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(adoptionPostingDao.save(any(AdoptionPosting.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateAdoptionPostingWithSameID() {
		AdoptionPosting ap = null;
		try {
			ap = service.createAdoptionPosting(USER_ID, USER_PET, USER_ADOPTER_LIST);
		} catch (IllegalArgumentException e) {
			assertNull(ap);
			assertEquals(e.getMessage(), "Posting with this ID already exists");
		}
	}
	
	@Test
	public void testCreateAdoptionPostingWithoutPet() {
		AdoptionPosting ap = null;
		Integer ID = 323;
		Pet pet = null;
		List<Adopter> adopterList = new ArrayList<Adopter>();
		
		try {
			ap = service.createAdoptionPosting(ID, pet, adopterList);
		} catch (IllegalArgumentException e) {
			assertNull(ap);
			assertEquals(e.getMessage(), "Posting requires a pet");
		}
	}
	
	@Test
	public void testCreateAdoptionPostingWithNonexisingPet() {
		AdoptionPosting ap = null;		
		try {
			ap = service.createAdoptionPosting(NONEXISTING_ID, NONEXISTING_PET, NONEXISTING_ADOPTER_LIST);
		} catch (IllegalArgumentException e) {
			assertNull(ap);
			assertEquals(e.getMessage(), "Posting requires a pet stored in the database");
		}
	}
	
	@Test
	// Testing updating a AdoptionPosting
	public void testUpdateAdoptionPosting() {
		AdoptionPosting ap = new AdoptionPosting();
		try {
			service.updatePosting(ap);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot update posting that is not in the database");
		}
	}
	
	@Test
	// Testing if you can get a non-existing AdoptionPosting
	public void testGetNonExistingAdoptionPosting() {
		assertNull(service.getPostingById(NONEXISTING_ID));
	}
	
	@Test
	//testing if you can get an existing AdoptionPosting
	public void testExistingAdoptionPosting() {
		assertEquals(USER_ID, service.getPostingById(USER_ID).getId());
	}
	
}
