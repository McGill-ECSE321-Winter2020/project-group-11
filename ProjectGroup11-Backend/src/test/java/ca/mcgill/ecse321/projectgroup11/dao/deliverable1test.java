package ca.mcgill.ecse321.projectgroup11.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup11.javacode.Address;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class deliverable1test {
	@Autowired
	private AdoptionPostingRepository adoptionPostingRepository;
	@Autowired
	private PetsRepository petsRepository;
	@Autowired
	private PetProfileRepository petProfileRepository;
	@Autowired
	private ShelterRepository shelterRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountUserRepository userRepository;
	

	@AfterEach
	@BeforeEach
	public void clearDatabase() {
		// We clear in this order => adoption_posting, pet, pet_profile, shelter, address, user
		petsRepository.clear();
		//adoptionPostingRepository.deleteAll();
		//petRepository.deleteAll();
		//petProfileRepository.deleteAll();
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

		Pet dog = petsRepository.createPet(20, 30);
		dog =null;
		dog = petsRepository.findPet(20);
		assertNotNull(dog);
		assertEquals(20 , dog.getId());
		assertEquals(30, dog.getAdoptionPosting().getId());	
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
	
	@Test
	public void testPersistAndLoadShelter() {
		
		Shelter s = new Shelter();
		
		Pet p = petsRepository.createPet(50, 40);
		Set<Pet> own = new HashSet<Pet>();
		
		s.setId(20);
	
		
		own.add(p);
		s.setPet(own);
		
		Manager m = new Manager();
		m.setDescription("I am a manager");
		m.setEmailAddress("yoohoo@yahoo.com");
		m.setFirstName("RandomManage");
		m.setLastName("Yeeeeee");
		m.setUserID(20);
		
		
		m.setShelter(s);
		s.setManager(m);
		
		userRepository.save(m);
		m = null;
		m = (Manager) userRepository.findAccountUserByuserID(20);
		
		
		//Ok, I'm noting here what happened:
		//Basically, there was this recurring error of shelter being unable
		//to find manager, even though it was clearly in the database.
		//The problem was that it was looking for a manager table.
		//I fixed this by making the getManager() method return an
		//Account User - thereby making it look for an object in the AccountUser table
		
		s = shelterRepository.save(s);
		s= null;
		s= shelterRepository.findShelterById(20);
		
		assertNotNull(s);
		assertEquals(20 , s.getId());
		
	}
	
	
	@Test
	public void testPersistAndLoadAddress() {
		Address a = new Address();
		a.setCity("Montreal");
		a.setId(50);
		a.setPostalCode("H2E1Z1");
		a.setProvince("Quebec");
		a.setStreetNumber(7501);
		a.setStreet("Rue Rousselot");
		
		
		a = addressRepository.save(a);
		a = null;
		a = addressRepository.findAddressById(50);
		assertNotNull(a);
		assertEquals("Montreal" , a.getCity());
		assertEquals("H2E1Z1", a.getPostalCode());
		assertEquals(50, a.getId());
		assertEquals("Rue Rousselot", a.getStreet());
		assertEquals(7501, a.getStreetNumber());
		assertEquals("Quebec", a.getProvince());
		
		
		
	}
	@Test
	public void testPersistAndLoadAdopter() {
		Adopter a = new Adopter();
		a.setEmailAddress("a@hotmail.com");
		a.setDescription("ok");
		a.setFirstName("jean");
		a.setLastName("dam");
		a.setPhoneNumer("514400230");
		a.setUserID(50);

		a = userRepository.save(a);
		a = null;
		a = (Adopter) userRepository.findAccountUserByuserID(50);
		
		assertNotNull(a);
		assertEquals("a@hotmail.com" , a.getEmailAddress());
		assertEquals("ok" ,a.getDescription());
		assertEquals("jean", a.getFirstName());
		assertEquals("dam", a.getLastName());
		assertEquals("514400230", a.getPhoneNumer());
		assertEquals(50, a.getUserID());
		
	}
	
	@Test
	public void testPersistAndLoadManager() {
		Manager a = new Manager();
		a.setEmailAddress("a@hotmail.com");
		a.setDescription("ok");
		a.setFirstName("jean");
		a.setLastName("dam");
		a.setPhoneNumer("514400230");
		a.setUserID(50);
		
		a = userRepository.save(a);
		a = null;
		a = (Manager) userRepository.findAccountUserByuserID(50);
		
		assertNotNull(a);
		assertEquals("a@hotmail.com" , a.getEmailAddress());
		assertEquals("ok" ,a.getDescription());
		assertEquals("jean", a.getFirstName());
		assertEquals("dam", a.getLastName());
		assertEquals("514400230", a.getPhoneNumer());
		assertEquals(50, a.getUserID());
		
		
		
	}
	
	@Test
	public void testPersistAndLoadPetProfile() {
		
		PetProfile p = new PetProfile();
		p.setApartment(true);
		p.setBreed("normal");
		p.setDescription("ok");
		p.setHealthConcerns("NA");
		p.setId(20);
		p.setHighEnergy(true);
		p.setKidsOkay(true);
		p.setPetsOkay(false);
		p.setPhotoURL("index.html");
		p.setName("baki");
		
		
		p = petProfileRepository.save(p);
		p = null;
		p = petProfileRepository.findPetProfileById(20);
		
		assertNotNull(p);
		assertEquals(true, p.getApartment());
		assertEquals("normal", p.getBreed());
		assertEquals("ok", p.getDescription());
		assertEquals("NA", p.getHealthConcerns());
		assertEquals(20, p.getId());
		assertEquals(true, p.getHighEnergy());
		assertEquals(true, p.getKidsOkay());
		assertEquals(false, p.getPetsOkay());
		assertEquals("index.html" , p.getPhotoURL());
		assertEquals("baki" , p.getName());


		
	}
	
	

}
