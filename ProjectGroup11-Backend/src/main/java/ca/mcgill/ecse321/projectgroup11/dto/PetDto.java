package ca.mcgill.ecse321.projectgroup11.dto;

public class PetDto {
	private Integer id;
	private OwnerDto owner;
	private ShelterDto shelter;
	private AdoptionPostingDto adoptionPosting;
	private PetProfileDto petProfile;

	public PetDto(Integer id) {
		this.id = id;
	}
	public PetDto(Integer id, PetProfileDto description) {
		this.id = id;
		this.petProfile = description;
	}
	public PetDto(Integer id, PetProfileDto description, OwnerDto owner) {
		this.id = id;
		this.owner = owner;
		this.petProfile = description;
	}
	public PetDto(Integer id, PetProfileDto description, ShelterDto shelter) {
		this.id = id;
		this.shelter = shelter;
		this.petProfile = description;
	}
	public PetDto(Integer id, PetProfileDto description, OwnerDto owner, AdoptionPostingDto posting) {
		this.id = id;
		this.owner = owner;
		this.petProfile = description;
		this.adoptionPosting = posting;
	}
	public PetDto(Integer id, PetProfileDto description, ShelterDto shelter, AdoptionPostingDto posting) {
		this.id = id;
		this.shelter = shelter;
		this.petProfile = description;
		this.adoptionPosting = posting;
	}
	
	public OwnerDto getOwner() {
		return this.owner;
	}
	public ShelterDto getShelterDto() {
		return this.shelter;
	}
	public Integer getId() {
		return this.id;
	}
	public AdoptionPostingDto getAdoptionPosting() {
		return this.adoptionPosting;
	}
	public PetProfileDto getPetProfile() {
		return this.petProfile;
	}
}
