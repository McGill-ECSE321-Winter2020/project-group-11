package ca.mcgill.ecse321.projectgroup11.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup11.dao.AccountUserRepository;
import ca.mcgill.ecse321.projectgroup11.dao.AdoptionPostingRepository;
import ca.mcgill.ecse321.projectgroup11.dao.PetProfileRepository;
import ca.mcgill.ecse321.projectgroup11.dao.PetsRepository;
import ca.mcgill.ecse321.projectgroup11.dao.ShelterRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;

@Service
public class PetService {
	@Autowired
	PetsRepository petRepo;
	@Autowired
	PetProfileRepository profileRepo;
	@Autowired
	ShelterRepository shelterRepo;
	@Autowired
	AdoptionPostingRepository postRepo;
	@Autowired
	AccountUserRepository managerRepo;
	@Autowired
	AccountUserService userService;
	
	//List of valid animal types for the pet profile
	private static final String[] animalTypes = {
			"dog", "cat", "rodent", "snake", "bird", "turtle", "fish"
	};
	
	
	
	//Get all methods
	@Transactional
	public List<Pet> getAllPets() {
		ArrayList<Pet> pets = new ArrayList<>();
		List<Pet> iterPets = petRepo.findAll();
		
		//Convert to array list
		for(Pet pet : iterPets) {
			pets.add(pet);
		}

		return pets;
	}
	@Transactional
	public List<PetProfile> getAllPetProfiles() {
		ArrayList<PetProfile> pets = new ArrayList<>();
		Iterable<PetProfile> iterPets = profileRepo.findAll();
		
		//Convert to array list
		for(PetProfile pet : iterPets) {
			pets.add(pet);
		}

		return pets;
	}
	@Transactional
	public List<Shelter> getAllShelters() {
		ArrayList<Shelter> shelters = new ArrayList<>();
		Iterable<Shelter> iterShel = shelterRepo.findAll();
		
		//Convert to array list
		for(Shelter s : iterShel) {
			shelters.add(s);
		}

		return shelters;
	}
	@Transactional
	public List<AdoptionPosting> getAllAdoptionPostings() {
		ArrayList<AdoptionPosting> posts = new ArrayList<>();
		Iterable<AdoptionPosting> iterPost = postRepo.findAll();
		
		//Convert to array list
		for(AdoptionPosting p : iterPost) {
			posts.add(p);
		}

		return posts;
	}
	
	//Search Individual methods
	@Transactional
	public Pet getPetById(Integer ID) {
		if(ID == null) return null;
		return petRepo.findPet(ID);
	}
	@Transactional
	public PetProfile getPetProfileById(Integer ID) {
		if(ID == null) return null;
		return profileRepo.findPetProfileById(ID);
	}
	@Transactional
	public Shelter getShelterById(Integer ID) {
		if(ID == null) return null;
		return shelterRepo.findShelterById(ID);
	}
	@Transactional
	public AdoptionPosting getPostingById(Integer ID) {
		if(ID == null) return null;
		return postRepo.findAdoptionPostingById(ID);
	}
	@Transactional
	public Pet getPetByProfile(Integer profileID) {
		List<Pet> allPets = this.getAllPets();
		for(Pet p : allPets) {
			if(p.getPetProfile().getId() == profileID) return p;
		}
		throw new IllegalArgumentException("No Pet has this pet profile");
	}
	
	
	@Transactional
	public List<Adopter> getPetApplicants(Integer ID) {
		if(ID == null ) return null;
		Pet p = petRepo.findPet(ID);
		Set<Adopter> adopters = null;
		if(p != null) adopters = p.getAdoptionPosting().getAdopters();
		else return null;
		if(adopters == null) return null;
		
		ArrayList<Adopter> candidates = new ArrayList<>();
		for(Adopter a : adopters) {
			candidates.add(a);
		}
		
		return candidates;
	}
	
	
	
	//Creation Methods
	@Transactional
	public Pet createPet(Integer ID, Owner owner, Shelter shelter, AdoptionPosting aPost, PetProfile profile) {
		
		if(profile == null) {
			throw new IllegalArgumentException("Please create a pet profile before creating a pet object");
		}
		
		Pet p = new Pet();
		
		if(petRepo.findPet(ID) != null && petRepo.findPet(ID).getId() == ID) {
			throw new IllegalArgumentException("Pet ID already exists");
		}
		
		p.setId(ID);
		if(owner == null) {
			if(shelter == null) {
				throw new IllegalArgumentException("Pet must have an owner or shelter");
			}
			p.setShelter(shelter);
		} else {
			//Owner is not null
			if(shelter != null) {
				throw new IllegalArgumentException("Pet cannot have both owner AND shelter");
			}
			p.setOwner(owner);
		}
		
		p.setAdoptionPosting(aPost);
		
		return petRepo.save(p, profile);
	}
	
	@Transactional
	public PetProfile createPetProfile(Integer ID, String name, 
									   String type, String description, 
									   String photoURL, String breed, 
									   Boolean apartment, Boolean kidOk, 
									   Boolean petOk, Boolean highE, 
									   String health) {
		if( profileRepo.findPetProfileById(ID) != null && profileRepo.findPetProfileById(ID).getId() == ID) {
			throw new IllegalArgumentException("Profile with same ID already exists");
		}
		
		if (ID == null &&
		   (name == null || name == "" || name == " ") &&
		   (type == null || type == "" || type == " ") &&
		   (description == null || description == "" || description == " ") &&
		   (photoURL == null || photoURL == "" || photoURL == " ") &&
		   (breed == null || breed == "" || breed == " ") &&
		   apartment == null &&
		   kidOk == null &&
	       petOk == null &&
		   highE == null &&
		   (health == null || health == "" || health == " ")) {
				throw new IllegalArgumentException("PetProfile cannot be empty!");
		}

		PetProfile p = new PetProfile();
		p.setId(ID);
		p.setName(name);
		p.setType(type);
		p.setDescription(description);
		p.setPhotoURL(photoURL);
		p.setBreed(breed);
		p.setApartment(apartment);
		p.setKidsOkay(kidOk);
		p.setPetsOkay(petOk);
		p.setHighEnergy(highE);
		p.setHealthConcerns(health);
		validateProfile(p);

		return profileRepo.save(p);
	}
	
	@Transactional
	public PetProfile createPetProfile(Integer ID, String name, String type, String description) {
		if( profileRepo.findPetProfileById(ID) != null && profileRepo.findPetProfileById(ID).getId() == ID) {
			throw new IllegalArgumentException("Profile with same ID already exists");
		}
		
		if (ID == null &&
		   (name == null || name == "" || name == " ") &&
		   (type == null || type == "" || type == " ") &&
		   (description == null || description == "" || description == " ")) {
				throw new IllegalArgumentException("PetProfile cannot be empty!");
		}

		PetProfile p = new PetProfile();
		p.setId(ID);
		p.setName(name);
		p.setType(type);
		p.setDescription(description);
		validateProfile(p);

		return profileRepo.save(p);
	}
	
	
	@Transactional
	/**
	 * Deletes a pet
	 * @param ID - ID of pet to delete
	 */
	public AdoptionPosting addPostingApplicant(Integer postingID, Integer adopterID) {
		
		if(postingID == null || this.getPostingById(postingID) == null) {
			throw new IllegalArgumentException("Cannot update pet that is not in database");
		}
		AdoptionPosting a = this.getPostingById(postingID);
		
		if(adopterID == null || userService.getAdopterByID(adopterID) == null) {
			throw new IllegalArgumentException("Cannot add applicant that is not in database");
		}
		
		Set<Adopter> applicants = new HashSet<>();
		if(a.getAdopters() != null) {
			for(Adopter adopter : a.getAdopters()) {
				applicants.add(adopter);
			}
		}
		applicants.add(userService.getAdopterByID(adopterID));
		a.setAdopters(applicants);
		
		return postRepo.save(a);
	}
	
	
	@Transactional
	/**
	 * Deletes a pet
	 * @param ID - ID of pet to delete
	 */
	public void deletePet(Integer ID) {
		if(ID == null || this.getPetById(ID) == null) {
			throw new IllegalArgumentException("Cannot delete pet that is not in database");
		}
		Pet p = this.getPetById(ID);
		if(p.getAdoptionPosting() != null) postRepo.delete(p.getAdoptionPosting());
		profileRepo.delete(p.getPetProfile());
		petRepo.deletePetById(ID);
	}
	
	
	@Transactional
	public Shelter createShelter(Integer ID, List<Pet> pets, Manager manager) {

		Shelter s = new Shelter();
		if(shelterRepo.findShelterById(ID) != null && shelterRepo.findShelterById(ID).getId() == ID) {
			throw new IllegalArgumentException("Shelter with this ID already exists");
		}
		s.setId(ID);
		if(pets != null) {
			if(pets.size() == 0) {
				throw new IllegalArgumentException("Please do not create an empty set of pets");
			}
			Set<Pet> petsList = new HashSet<>();
			petsList.addAll(pets);
			s.setPet(petsList);
		}
		if(manager != null) {
			try   {
				Manager a = (Manager) userService.getManagerByID(manager.getUserID());
			}
			catch(Exception e) {
				if(e.getMessage() == null) {
					throw new IllegalArgumentException("Manager must be saved first");
				}
			}
			s.setManager(manager);

			
			
			s.setManager(manager);
		}

		return shelterRepo.save(s);
	}
	@Transactional
	public Shelter createShelter(Integer ID) {

		Shelter s = new Shelter();
		if( shelterRepo.findShelterById(ID) != null && shelterRepo.findShelterById(ID).getId() == ID) {
			throw new IllegalArgumentException("Shelter with this ID already exists");
		}
		s.setId(ID);

		return shelterRepo.save(s);
	}

	@Transactional
	public AdoptionPosting createAdoptionPosting(Integer ID, Pet p, List<Adopter> adopters) {

		AdoptionPosting a = new AdoptionPosting();

		if (postRepo.findAdoptionPostingById(ID) != null && postRepo.findAdoptionPostingById(ID).getId() == ID) {
			throw new IllegalArgumentException("Posting with this ID already exists");
		}
		a.setId(ID);
		if (p == null) {
			throw new IllegalArgumentException("Posting requires a pet");
		}
		if (this.getPetById(p.getId()) == null) {
			throw new IllegalArgumentException("Posting requires a pet stored in the database");
		}
		a.setPet(p);

		if (adopters != null) {
			Set<Adopter> aSet = new HashSet<>();
			for (Adopter adopt : adopters) {
				if (userService.getAdopterByID(adopt.getUserID()) == null) {
					throw new IllegalArgumentException("Please create adopters before applying them to posts");
				}
				aSet.add(adopt);
			}
			a.setAdopters(aSet);
		}

		return postRepo.save(a);
	}
	@Transactional
	public AdoptionPosting createAdoptionPosting(Integer ID, Pet p) {

		AdoptionPosting a = new AdoptionPosting();

		if (postRepo.findAdoptionPostingById(ID) != null && postRepo.findAdoptionPostingById(ID).getId() == ID) {
			throw new IllegalArgumentException("Posting with this ID already exists");
		}
		a.setId(ID);
		if (p == null) {
			throw new IllegalArgumentException("Posting requires a pet");
		}
		if (this.getPetById(p.getId()) == null) {
			throw new IllegalArgumentException("Posting requires a pet stored in the database");
		}
		a.setPet(p);

		return postRepo.save(a);
	}
	
	
	
	
	//Update Methods
	@Transactional
	public Pet updatePet(Pet p) {
		if(p == null || this.getPetById(p.getId()) == null) {
			throw new IllegalArgumentException("Cannot update pet that is not in the database");
		}
		return petRepo.save(p, p.getPetProfile());
	}
	@Transactional
	public PetProfile updateProfile(PetProfile p) {
		if(p == null || this.getPetProfileById(p.getId()) == null) {
			throw new IllegalArgumentException("Cannot update petProfile that is not in the database");
		}
		return profileRepo.save(p);
	}
	@Transactional
	public Shelter updateShelter(Shelter s) {
		if(s == null || this.getShelterById(s.getId()) == null) {
			throw new IllegalArgumentException("Cannot update shelter that is not in the database");
		}
		return shelterRepo.save(s);
	}
	@Transactional
	public AdoptionPosting updatePosting(AdoptionPosting a) {
		if(a == null || this.getPostingById(a.getId()) == null) {
			throw new IllegalArgumentException("Cannot update posting that is not in the database");
		}
		return postRepo.save(a);
	}
	
	
	
	private void validateProfile(PetProfile profile) {
		if(profile == null) {
			throw new IllegalArgumentException("Pet Profile cannot be null");
		}
	
		//Search types to see if current type matches one
		if(profile.getType() != null) { 
			boolean foundType = false;
			String curType = profile.getType().toLowerCase();
			for(String aniType : animalTypes) {
				if(curType.equals(aniType)) {
					foundType = true;
					break;
				}
			}
			if(!foundType) {
				throw new IllegalArgumentException("Pet Profile must have an valid animal type");
			}
		} else {
			throw new IllegalArgumentException("Pet Profile must have an animal type");
		}
		
		
		if(profile.getName() == null || !profile.getName().matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException("Not valid pet name");
		}
		if(profile.getBreed() != null && !profile.getBreed().matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Not alphabetic pet name");
		}
		
		if(profile.getDescription() != null) {
			int descLen = profile.getDescription().length();
			if(descLen <= 20 || descLen >= 1000) {
				throw new IllegalArgumentException("Not alphabetic pet name");
			}
		}
 		if(profile.getHealthConcerns() != null) {
 			int helthyBoiLen = profile.getHealthConcerns().length();
			if(helthyBoiLen <= 20 || helthyBoiLen >= 1000) {
				throw new IllegalArgumentException("Not alphabetic pet name");
			}
 		}
 		
 		//Checks for proper file extension and at least one '/' and '.' in a valid place
 		String url = profile.getPhotoURL();
 		if(url != null) {
 			int dashInd =  url.indexOf('/');
	 		if(dashInd == -1 || dashInd == 0) {
	 			throw new IllegalArgumentException("Photo URL does not contain a '/'");
	 		}
			String extension = url.substring(url.lastIndexOf('.') + 1);
	 		if(!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg")) {
	 			throw new IllegalArgumentException("Please use a png or jpg file for pictures");
	 		}
 		}
 		
		
	}
}
