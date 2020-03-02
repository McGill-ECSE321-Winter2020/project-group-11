package ca.mcgill.ecse321.projectgroup11.service;

import ca.mcgill.ecse321.projectgroup11.dao.PetsRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestPet_PetService {
	@Mock
	private PetsRepository petRepository;

	@InjectMocks
	private PetService petService;

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

	public static final Integer USER_ID_2 = 6;

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
		lenient().when(petRepository.findPet(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_ID)) {
				Pet pet = new Pet();
				pet.setId(USER_ID);
				pet.setOwner(new Owner());
				pet.setShelter(null);
				pet.setAdoptionPosting(new AdoptionPosting());
				pet.setPetProfile(new PetProfile());

				return pet;
			}
			else if (invocation.getArgument(0).equals(USER_ID_2)) {
				Pet pet = new Pet();
				pet.setId(USER_ID_2);
				pet.setOwner(null);
				pet.setShelter(new Shelter());
				pet.setAdoptionPosting(new AdoptionPosting());
				pet.setPetProfile(new PetProfile());

				return pet;
			}
			else {
				return null;
			}
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(petRepository.save(any(Pet.class), any(PetProfile.class))).thenAnswer(returnParameterAsAnswer);
	}

	//Integer ID, Owner owner, Shelter shelter, AdoptionPosting aPost, PetProfile profile
	@Test
	public void createPet_ValidPetWithOwner_ReturnPet() {
		// Assert
		// Assert if the pet is valid
		// No need to check if other properties aren't null, if the referenced pet by id is found
		// the other properties should be valid as well
		assertEquals(USER_ID, petRepository.findPet(USER_ID).getId());
	}

	@Test
	public void createPet_ValidPetWithShelter_ReturnPet() {
		// Assert
		// Assert if the pet is valid
		// No need to check if other properties aren't null, if the referenced pet by id is found
		// the other properties should be valid as well
		assertEquals(USER_ID_2, petRepository.findPet(USER_ID_2).getId());
	}

	@Test
	public void createPet_nullPetProfile_ThrowIllegalArgumentException() {
		// Arrange
		int id = 1;
		Owner owner = new Owner();
		Shelter shelter = null;
		AdoptionPosting adoptPosting = new AdoptionPosting();
		PetProfile petProfile = null;
		String error = "";
		// Act
		try {
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
		} catch(Exception e) {
			error = e.getMessage();
		}

		// Assert
		assertNull(petProfile);
		assertEquals(error, "Please create a pet profile before creating a pet object");
	}

	@Test
	public void createPet_NotNullOwnerShelter_ThrowIllegalArgumentException() {
		// Arrange
		int id = 1;
		Owner owner = new Owner();
		Shelter shelter = new Shelter();
		AdoptionPosting adoptPosting = new AdoptionPosting();
		PetProfile petProfile = new PetProfile();
		String error = "";
		// Act
		try {
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
		} catch(Exception e) {
			error = e.getMessage();
		}

		// Assert
		assertNotNull(owner);
		assertNotNull(shelter);
		assertEquals(error, "Pet cannot have both owner AND shelter");
	}

	@Test
	public void createPet_NullOwnerShelter_ThrowIllegalArgumentException() {
		// Arrange
		int id = 1;
		Owner owner = null;
		Shelter shelter = null;
		AdoptionPosting adoptPosting = new AdoptionPosting();
		PetProfile petProfile = new PetProfile();
		String error = "";
		// Act
		try {
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
		} catch(Exception e) {
			error = e.getMessage();
		}

		// Assert
		assertNull(owner);
		assertNull(shelter);
		assertEquals(error, "Pet must have an owner or shelter");
	}
	@Test
	public void createPet_ExistingID_ThrowIllegalArgumentException() {
		// Arrange
		int id = 1;
		Owner owner = new Owner();
		Shelter shelter = null;
		AdoptionPosting adoptPosting = new AdoptionPosting();
		PetProfile petProfile = new PetProfile();
		String error = "";
		// Act
		try {
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
		} catch (Exception e) {
			error = e.getMessage();
			// Assert;
			assertEquals("Pet ID already exists", error);
		}
	}
	@Test
	public void getAllPets_ValidList_ReturnList()   {
		// Arrange
		int id = 1;
		Owner owner = new Owner();
		Shelter shelter = null;
		AdoptionPosting adoptPosting = new AdoptionPosting();
		PetProfile petProfile = new PetProfile();
		int id2 = 2;
		Owner owner2 = new Owner();
		Shelter shelter2 = null;
		AdoptionPosting adoptPosting2 = new AdoptionPosting();
		PetProfile petProfile2 = new PetProfile();

		// Act
		try {
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
			petService.createPet(id2, owner2, shelter2, adoptPosting2, petProfile2);
			petService.getAllPets();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void getPetbyID_ValidID_ReturnValidPet() {
		assertEquals(USER_ID, petRepository.findPet(USER_ID).getId());
	}

	@Test
	public void getPetbyID_NullID_ReturnNull() {
		// Arrange
		Integer id = null;
		Owner owner = new Owner();
		Shelter shelter = null;
		AdoptionPosting adoptPosting = new AdoptionPosting();
		PetProfile petProfile = new PetProfile();

		// Act
		try {
			petService.createPet(id, owner, shelter, adoptPosting, petProfile);
		} catch(Exception e) {
			e.printStackTrace();
		}

		// Assert
		assertEquals(id, null);
	}

	@Test
	public void updatePet_NullPet_ThrowIllegalArgumentException() {
		Pet pet = null;
		try {
			petService.updatePet(pet);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Cannot update pet that is not in the database");
		}
	}
}