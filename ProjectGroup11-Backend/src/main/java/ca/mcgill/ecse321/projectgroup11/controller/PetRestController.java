package ca.mcgill.ecse321.projectgroup11.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup11.dto.AdopterDto;
import ca.mcgill.ecse321.projectgroup11.dto.AdoptionPostingDto;
import ca.mcgill.ecse321.projectgroup11.dto.ManagerDto;
import ca.mcgill.ecse321.projectgroup11.dto.OwnerDto;
import ca.mcgill.ecse321.projectgroup11.dto.PetDto;
import ca.mcgill.ecse321.projectgroup11.dto.PetProfileDto;
import ca.mcgill.ecse321.projectgroup11.dto.ShelterDto;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;
import ca.mcgill.ecse321.projectgroup11.service.AccountUserService;
import ca.mcgill.ecse321.projectgroup11.service.PetService;

@CrossOrigin(origins = "*")
@RestController
public class PetRestController {

	@Autowired
	private AccountUserService userService;
	
	@Autowired
	private PetService petService;
	
	@GetMapping(value = { "/pets", "/pets/" })
	public List<PetDto> getAllPets() {
		return petService.getAllPets().stream().map(p -> convertToPetDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/pets/{ID}", "/pet/{ID}/" })
	public PetDto getPetById(@PathVariable("ID") Integer ID) {
		return convertToPetDto(petService.getPetById(ID));
	}
	@GetMapping(value = { "/petProfiles", "/petProfiles/" })
	public List<PetProfileDto> getAllPetProfiles() {
		return petService.getAllPetProfiles().stream().map(p -> convertToPetProfileDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/petProfiles/{ID}", "/petProfiles/{ID}/" })
	public PetProfileDto getPetProfileById(@PathVariable("ID") Integer ID) {
		return convertToPetProfileDto(petService.getPetProfileById(ID));
	}
	
	@GetMapping(value = { "/shelters", "/shelters/" })
	public List<ShelterDto> getAllShelters() {
		return petService.getAllShelters().stream().map(p -> convertToShelterDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/shelters/{ID}", "/shelters/{ID}/" })
	public ShelterDto getShelterById(@PathVariable("ID") Integer ID) {
		return convertToShelterDto(petService.getShelterById(ID));
	}
	
	@GetMapping(value = { "/adoptPosts", "/adoptPosts/" })
	public List<AdoptionPostingDto> getAllAdoptionPostings() {
		return petService.getAllAdoptionPostings().stream().map(p -> convertToAdoptionPostingDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/adoptPosts/{ID}", "/adoptPosts/{ID}/" })
	public AdoptionPostingDto getAdoptionPostingById(@PathVariable("ID") Integer ID) {
		return convertToAdoptionPostingDto(petService.getPostingById(ID));
	}
	
	
	
	@PostMapping(value = { "/shelterPet/{ID}/{profileID}/{shelterID}/{postingID}", "/shelterPet/{ID}/{profileID}/{shelterID}/{postingID}/" })
	public PetDto createShelterPet(@PathVariable("ID") Integer ID, @PathVariable("profileID") Integer profileID, 
								   @PathVariable("shelterID") Integer shelterID, @PathVariable("postingID") Integer postingID) throws IllegalArgumentException {
		//Null check shelter
		Shelter s = petService.getShelterById(shelterID);
		if(s == null) s = petService.createShelter(shelterID);
		//Set up Adoption posting - should usually go to null branch
		AdoptionPosting a = petService.getPostingById(postingID);
		if(a == null) { 
			Pet p = petService.createPet(ID, null, s, null, petService.getPetProfileById(postingID));
			
			a = petService.createAdoptionPosting(postingID, p);
			p.setAdoptionPosting(a);
			return convertToPetDto(petService.updatePet(p));
		} else {
			Pet p = petService.createPet(ID, null, s, a, petService.getPetProfileById(postingID));
			return convertToPetDto(p);
		}
	}
	@PostMapping(value = { "/ownerPet/{ID}/{profileID}/{ownerID}/{postingID}", "/ownerPet/{ID}/{profileID}/{ownerID}/{postingID}/" })
	public PetDto createOwnerPet(@PathVariable("ID") Integer ID, @PathVariable("profileID") Integer profileID, 
								   @PathVariable("ownerID") Integer ownerID, @PathVariable("postingID") Integer postingID) throws IllegalArgumentException {
		//Null check owner
		Owner o = userService.getOwnerByID(ownerID);
		if(o == null) {
			throw new IllegalArgumentException("Please create owner before adding pets");
		}
		//Set up Adoption posting - should usually go to null branch
		AdoptionPosting a = petService.getPostingById(postingID);
		if(a == null) { 
			Pet p = petService.createPet(ID, o, null, null, petService.getPetProfileById(postingID));
			
			a = petService.createAdoptionPosting(postingID, p);
			p.setAdoptionPosting(a);
			return convertToPetDto(petService.updatePet(p));
		} else {
			Pet p = petService.createPet(ID, o, null, a, petService.getPetProfileById(postingID));
			return convertToPetDto(p);
		}
	}
	
	@PostMapping(value = { "/addApplicant/{postID}/{adopterID}", "/addApplicant/{postID}/{adopterID}/" })
	public AdoptionPostingDto createOwnerPet(@PathVariable("postID") Integer postID, 
								 @PathVariable("adopterID") Integer adoptID) throws IllegalArgumentException {
		return convertToAdoptionPostingDto(petService.addPostingApplicant(postID, adoptID));
	}
	
	//4 create profile methods, each more general
	@PostMapping(value = { "/profile/{ID}/{name}/{type}/{description}/{breed}/{photoURL}/{apartment}/{kidsOK}/{petsOK}/{highEnergy}/{healthConcerns}", "/profile/{ID}/{name}/{type}/{description}/{breed}/{photoURL}/{apartment}/{kidsOK}/{petsOK}/{highEnergy}/{healthConcerns}/" })
	public PetProfileDto createPetProfile(@PathVariable("ID") Integer ID, @PathVariable("name") String name,  @PathVariable("type") String type,
								   @PathVariable("description") String description, @PathVariable("breed") String breed, @PathVariable("photURL") String photoURL,
								   @PathVariable("apartment") Boolean apartment, @PathVariable("kidsOK") Boolean kidOk, @PathVariable("petsOK") Boolean petOk,
								   @PathVariable("highEnergy") Boolean highE, @PathVariable("healthConcerns") String health) throws IllegalArgumentException {
		return convertToPetProfileDto(
				petService.createPetProfile(ID, name, type, description, photoURL, breed, apartment, kidOk, petOk, highE, health)
				);
	}
	@PostMapping(value = { "/profile/{ID}/{name}/{type}/{description}/{breed}/{photoURL}/{apartment}/{healthConcerns}", "/profile/{ID}/{name}/{type}/{description}/{breed}/{photoURL}/{apartment}/{healthConcerns}/" })
	public PetProfileDto createPetProfile2(@PathVariable("ID") Integer ID, @PathVariable("name") String name,  @PathVariable("type") String type,
								   @PathVariable("description") String description, @PathVariable("breed") String breed, @PathVariable("photURL") String photoURL,
								   @PathVariable("apartment") Boolean apartment, @PathVariable("healthConcerns") String health) throws IllegalArgumentException {
		return this.createPetProfile(ID, name, type, description, photoURL, breed, apartment, null, null, null, health);
	}
	@PostMapping(value = { "/profile/{ID}/{name}/{type}/{description}/{breed}/{healthConcerns}", "/profile/{ID}/{name}/{type}/{description}/{breed}/{healthConcerns}/" })
	public PetProfileDto createPetProfile3(@PathVariable("ID") Integer ID, @PathVariable("name") String name,  @PathVariable("type") String type,
								   @PathVariable("description") String description, @PathVariable("breed") String breed, @PathVariable("healthConcerns") String health) throws IllegalArgumentException {
		return this.createPetProfile2(ID, name, type, description, breed, null, null, health);
	}
	@PostMapping(value = { "/profile/{ID}/{name}/{type}/{description}", "/profile/{ID}/{name}/{type}/{description}/" })
	public PetProfileDto createPetProfile4(@PathVariable("ID") Integer ID, @PathVariable("name") String name,  @PathVariable("type") String type,
								   @PathVariable("description") String description) throws IllegalArgumentException {
		return this.createPetProfile3(ID, name, type, description, null, null);
	}
	
	
	@PostMapping(value = { "/delete/pet/{ID}", "/delete/pet/{ID}/" })
	public void deletePet(@PathVariable("ID") Integer PetID) throws IllegalArgumentException {
		petService.deletePet(PetID);
	}
	
	
	
	//CONVERSION METHODS
	
	private PetDto convertToPetDto(Pet p) {
		if(p == null) {
				throw new IllegalArgumentException("PetDto cannot be made for null pets");
		}
		
		PetDto dto = null;
		PetProfileDto profile = convertToPetProfileDto(p.getPetProfile());
		AdoptionPostingDto aDto = null;
		if(p.getAdoptionPosting() != null) {
			aDto = new AdoptionPostingDto(p.getAdoptionPosting().getId(), p.getAdoptionPosting().getAdopters().stream().map(a -> new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress())).collect(Collectors.toList())  );
		}
		OwnerDto owner = null;
		ShelterDto shelter = null;
		if(p.getOwner() != null) {
			owner = new OwnerDto(p.getOwner().getUserID(), 
								 p.getOwner().getFirstName(), 
								 p.getOwner().getLastName(), 
								 p.getOwner().getEmailAddress());
			
			dto = new PetDto(p.getId(), profile, owner, aDto);
		} else if (p.getShelter() != null) {
			shelter = new ShelterDto(p.getShelter().getId());
			
			dto = new PetDto(p.getId(), profile, shelter, aDto);
		} else {
			throw new IllegalArgumentException("Pet must have an owner or shelter!");
		}
		
		
		
		return dto;
		
	}
	
	private PetProfileDto convertToPetProfileDto(PetProfile p) {
		if(p == null) {
			throw new IllegalArgumentException("PetProfileDto cannot be made for null pet profiles");
		}
		PetProfileDto dto = new PetProfileDto(p.getId(), p.getName(), p.getType(), p.getDescription(), 
				 							  p.getPhotoURL(),p.getBreed(),p.getApartment(), p.getKidsOkay(), 
				 							  p.getPetsOkay(), p.getHighEnergy(), p.getHealthConcerns());
		
		return dto;
		
	}
	
	private ShelterDto convertToShelterDto(Shelter s) {
		if(s == null) {
			throw new IllegalArgumentException("ShelterDto cannot be made for null shelters");
		}
		ShelterDto dto = null;
		
		List<PetDto> pets = null;
		if(s.getPet() != null) {
					pets = s.getPet()
					.stream()
					.map( p -> new PetDto(p.getId()))
					.collect(Collectors.toList());
		}
		
		if(s.getManager() != null) {
			ManagerDto mDto = new ManagerDto(s.getManager().getUserID(), s.getManager().getFirstName(), 
											 s.getManager().getLastName(), s.getManager().getEmailAddress());
			
			dto = new ShelterDto(s.getId(), mDto, pets);
		} else {
			dto = new ShelterDto(s.getId(), pets);
		}
		

		return dto;

	}

	private AdoptionPostingDto convertToAdoptionPostingDto(AdoptionPosting p) {
		if(p == null) {
			throw new IllegalArgumentException("AdoptionPostingDto cannot be made for null postings");
		}
		AdoptionPostingDto dto = null;
		
		List<AdopterDto> adopters = null;
		if(p.getAdopters() != null) {
					adopters = p.getAdopters()
					.stream()
					.map( a -> new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress()))
					.collect(Collectors.toList());
			
		}
		if(p.getPet() == null) {
			throw new IllegalArgumentException("Adoption Posting should never have no pets");
		}
		dto = new AdoptionPostingDto(p.getId(), new PetDto(p.getPet().getId()), adopters);
		

		return dto;

	}
}
