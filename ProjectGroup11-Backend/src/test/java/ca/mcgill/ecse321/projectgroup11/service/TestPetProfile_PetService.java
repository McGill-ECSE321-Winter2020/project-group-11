package ca.mcgill.ecse321.projectgroup11.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
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
import ca.mcgill.ecse321.projectgroup11.dao.PetProfileRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.service.PetService;


/**
 *
 *@author ProjectGroup11
 *Tests the PetProfile methods in PetService
 *
 */

@ExtendWith(MockitoExtension.class)
public class TestPetProfile_PetService {
	@Mock
	private PetProfileRepository petProfileDao;

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

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(petProfileDao.save(any(PetProfile.class))).thenAnswer(returnParameterAsAnswer);
	}


	@Test
	// Testing the use of null imputs in the creation of a PetProfile using the long form
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
	// Testing the use of null inputs in the creation of a PetProfile using the short form
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
	// Testing the use of empty fields in the creation of a PetProfile using the long form
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

		assertEquals("PetProfile cannot be empty!", error);

	}

	@Test
	// Testing the use of empty fields in the creation of a PetProfile using the short form
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
		assertEquals("PetProfile cannot be empty!", error);

	}

	@Test
	// Testing the use of spaces in the creation of a PetProfile using the long form
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
		assertEquals("PetProfile cannot be empty!", error);

	}

	@Test
	// Testing the use of spaces in the creation of a PetProfile using the short form
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
		assertEquals("PetProfile cannot be empty!", error);

	}

	@Test
	// Testing if you can create the same PetProfile twice using the long form
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
	// Testing if you can create the same PetProfile twice using the short form
	public void testCreatePetProfileWithSameInfoShort() {
		PetProfile p = null;
		try {
			p = service.createPetProfile(USER_ID, USER_NAME, USER_TYPE, USER_DESCRIPTION);
		} catch (IllegalArgumentException e) {
			assertNull(p);
			assertEquals(e.getMessage(), "Profile with same ID already exists");
		}
	}

	@Test
	// Testing updating a PetProfile
	public void testUpdatePetProfile() {
		PetProfile p = new PetProfile();
		try {
			service.updateProfile(p);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot update petProfile that is not in the database");
		}
	}

	@Test
	// Testing if you can get a non-existing PetProfile
	public void testGetNonExistingPetProfile() {
		assertNull(service.getPetProfileById(NONEXISTING_ID));
	}

	@Test
	// Testing if you can get an existing PetProfile
	public void testGetExistingPetProfile() {
		assertEquals(USER_ID, service.getPetProfileById(USER_ID).getId());
	}

}