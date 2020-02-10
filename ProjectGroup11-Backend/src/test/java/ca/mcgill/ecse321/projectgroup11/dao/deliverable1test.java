package ca.mcgill.ecse321.projectgroup11.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

<<<<<<< HEAD
=======
import java.util.HashSet;
import java.util.Set;

>>>>>>> branch 'master' of https://github.com/McGill-ECSE321-Winter2020/project-group-11.git
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

<<<<<<< HEAD
=======
import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Address;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
>>>>>>> branch 'master' of https://github.com/McGill-ECSE321-Winter2020/project-group-11.git
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
<<<<<<< HEAD
=======
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
>>>>>>> branch 'master' of https://github.com/McGill-ECSE321-Winter2020/project-group-11.git
@ExtendWith(SpringExtension.class)
@SpringBootTest
class deliverable1test {
	@Autowired
	private AdoptionPostingRepository adoptionPostingRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private PetProfileRepository petProfileRepository;
	@Autowired
	private ShelterRepository shelterRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountUserRepository userRepository;
	

	@AfterEach
	public void clearDatabase() {
		// We clear in this order => adoption_posting, pet, pet_profile, shelter, address, user
		adoptionPostingRepository.deleteAll();
		petRepository.deleteAll();
		petProfileRepository.deleteAll();
		shelterRepository.deleteAll();
		addressRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadAdoptionPosting() {
		Integer testing = 800; // creating an ID for the post
		
		AdoptionPosting post = new AdoptionPosting();
		post.setId(testing);
		
		post = adoptionPostingRepository.save(post);

		post = null;

		
		
		post= adoptionPostingRepository.findAdoptionPostingById(testing);
		
		assertNotNull(post);
		assertEquals(testing, post.getId());
		
		
		
		
		
	}
	
	@Test 
	public void testPersistAndLoadPet() {
		AdoptionPosting a = new AdoptionPosting();
		
		Pet dog = new Pet();
		

		a.setId(50);
		dog.setId(50);

		a.setPet(dog);
		dog.setAdoptionPosting(a);
		a = adoptionPostingRepository.save(a);		
		dog = petRepository.save(dog);

		





		
	}
	@Test
	public void testPersistAndLoadOwner() {
		Owner hamza = new Owner();
		hamza.setFirstName("Jouer");
		hamza.setLastName("ballon");
		hamza.setDescription("for fun");
		hamza.setUserID(50);
		
		// Try creating a hashset of pets to verify if we have access to those fields
		userRepository.save(hamza);
		hamza = null;
		hamza = (Owner) userRepository.findAccountUserByuserID(50);
		assertNotNull(hamza);
		assertEquals(50, hamza.getUserID());
		assertEquals("Jouer", hamza.getFirstName());
		assertEquals("ballon", hamza.getLastName());
		assertEquals("for fun", hamza.getDescription());
		
		
		
		
		
		
		
		
		
		
	}
	

}
