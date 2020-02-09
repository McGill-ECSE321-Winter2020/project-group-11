package ca.mcgill.ecse321.projectgroup11.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
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
		Integer testing = 750; // creating an ID for the post
		
		AdoptionPosting post = new AdoptionPosting();
		
		post.setId(testing);
		adoptionPostingRepository.save(post);
		post = null;
		Owner adel = new Owner();
		adel.setUserID(testing);
		userRepository.save(adel);
		
		
		post= adoptionPostingRepository.findAdoptionPostingById(testing);
		
		assertNotNull(post);
		assertEquals(testing, post.getId());
		
		
		
		
		
	}
	

}
