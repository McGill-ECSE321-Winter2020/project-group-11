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
import ca.mcgill.ecse321.projectgroup11.dao.PetProfileRepository;
import ca.mcgill.ecse321.projectgroup11.dao.PetsRepository;
import ca.mcgill.ecse321.projectgroup11.dao.ShelterRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;
import ca.mcgill.ecse321.projectgroup11.service.PetService;


@ExtendWith(MockitoExtension.class)
public class TestPetProfile_PetService {
	@Mock
	private PetProfileRepository petProfileDao;
	private PetsRepository petDao;

	@InjectMocks
	private PetService service;

	
	public static final Integer USER_ID = 5;
	public static final String USER_NAME = "Bobo";
	public static final String USER_TYPE = "cat";
	public static final String USER_DESCRIPTION = "chubby";
	public static final String USER_PHOTOURL = "urlurl";
	public static final String USER_BREED = "street cat";
	public static final Boolean USER_APARTMENT = true;
	public static final Boolean USER_KIDOK = true;
	public static final Boolean USER_PETOK = true;
	public static final Boolean USER_HIGHE = false;
	public static final String USER_HEALTH = "chubby but healthy boi";
	
	public static final Integer NONEXISTING_ID = 20;
	public static final String NONEXISTING_NAME = "Caesar";
	public static final String NONEXISTING_TYPE = "dog";
	public static final String NONEXISTING_DESCRIPTION = "lazy";
	public static final String NONEXISTING_PHOTOURL = "urlurlurl";
	public static final String NONEXISTING_BREED = "German Shepherd";
	public static final Boolean NONEXISTING_APARTMENT = true;
	public static final Boolean NONEXISTING_KIDOK = true;
	public static final Boolean NONEXISTING_PETOK = true;
	public static final Boolean NONEXISTING_HIGHE = false;
	public static final String NONEXISTING_HEALTH = "Young healthy dog";
	
	
	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;

	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(petProfileDao.findPetProfileById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_ID)) {
				PetProfile petProfile = new PetProfile();
				petProfile.setId(USER_ID);
				petProfile.setName(USER_NAME);
				petProfile.setType(USER_TYPE);
				petProfile.setDescription(USER_DESCRIPTION);
				petProfile.setPhotoURL(USER_PHOTOURL);
				petProfile.setBreed(USER_BREED);
				petProfile.setApartment(USER_APARTMENT);
				petProfile.setKidsOkay(USER_KIDOK);
				petProfile.setPetsOkay(USER_PETOK);
				petProfile.setHighEnergy(USER_HIGHE);
				petProfile.setHealthConcerns(USER_HEALTH);
				
				return petProfile;
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
	public void testCreatePetProfileShort() {
		assertEquals(0, service.getAllPetProfiles().size());
		
		Integer ID = 66;
		String name = "Bobo";
		String type = "cat";
		String description = "chubby";		
		
		
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description);
		}	catch (Exception e) {
			fail();
		}
		assertNotNull(petProfile);
		assertEquals(ID, petProfile.getId());
		assertEquals(name, petProfile.getName());
		assertEquals(type, petProfile.getType());
		assertEquals(description, petProfile.getDescription());
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
	public void testCreatePetProfileNullShort() {
		Integer ID = null;
		String name = null;
		String type = null;
		String description = null;
		String error = null;
		PetProfile petProfile = null;

		
		try {
			petProfile = service.createPetProfile(ID, name, type, description);
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
	public void testCreatePetProfileEmptyShort() {
		Integer ID = null;
		String name = "";
		String type = "";
		String description = "";
		String error = null;
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description);
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
	
	@Test
	public void testCreatePetProfileSpacesShort() {
		Integer ID = null;
		String name = " ";
		String type = " ";
		String description = " ";
		String error = null;
		PetProfile petProfile = null;
		
		try {
			petProfile = service.createPetProfile(ID, name, type, description);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		assertNull(petProfile);
		//To correct
		assertEquals("PetProfile cannot be empty!", error);
		
	}
	
	@Test
	public void testCreatePetProfileWithSameInfo() {
		PetProfile p = null;
		try {
			p = service.createPetProfile(USER_ID, USER_NAME, USER_TYPE, USER_DESCRIPTION, USER_PHOTOURL, USER_BREED, USER_APARTMENT, USER_KIDOK, USER_PETOK, USER_HIGHE, USER_HEALTH);
		} catch (Exception e) {
			assertNull(p);
			assertEquals(e.getMessage(), "Profile with same ID already exists");
		}
	}
	
	@Test
	public void testCreatePetProfileWithSameInfoShort() {
		PetProfile p = null;
		try {
			p = service.createPetProfile(USER_ID, USER_NAME, USER_TYPE, USER_DESCRIPTION);
		} catch (Exception e) {
			assertNull(p);
			assertEquals(e.getMessage(), "Profile with same ID already exists");
		}
	}
	
	@Test
	public void testGetPetProfiles() {
		
	}
	
}