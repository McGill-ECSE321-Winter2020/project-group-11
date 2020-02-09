package ca.mcgill.ecse321.projectgroup11.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.User;

import org.junit.jupiter.api.Test;
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
	private UserRepository userRepository;
	

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
