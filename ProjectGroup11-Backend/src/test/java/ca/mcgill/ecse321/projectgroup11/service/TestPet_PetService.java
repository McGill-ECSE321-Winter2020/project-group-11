package ca.mcgill.ecse321.projectgroup11.service;

import ca.mcgill.ecse321.projectgroup11.dao.PetRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestPet_PetService {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private static final int USER_KEY = 5;
    private static final int NONEXISTING_KEY = 20;

    @BeforeEach
    public void setUp() throws Exception {
        //When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
        lenient().when(petRepository.findPet(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USER_KEY)) {
                Shelter shelter = new Shelter();
                shelter.setId(USER_KEY);
                return shelter;
            } else {
                return null;
            }
        });
    }

    //Integer ID, Owner owner, Shelter shelter, AdoptionPosting aPost, PetProfile profile
    @Test
    public void createPet_ValidPet_ReturnPet() {
        // Arrange
        int id = 1;
        Owner owner = new Owner();
        Shelter shelter = new Shelter();
        AdoptionPosting adoptPosting = new AdoptionPosting();
        PetProfile petProfile = new PetProfile();

        // Act
        try {
           petService.createPet(id, owner, shelter, adoptPosting, petProfile);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Assert
        // Assert if the pet is valid
        // No need to check if other properties aren't null, if the referenced pet by id is found
        // the other properties should be valid as well
        assertNotEquals(null, petRepository.findPet(id));
    }

    @Test
    public void createPet_nullPetProfile_ThrowIllegalArgumentException() {
        // Arrange
        int id = 1;
        Owner owner = new Owner();
        Shelter shelter = new Shelter();
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
}