package ca.mcgill.ecse321.projectgroup11.dto;

public class PetDto {
	private Integer id;
	private OwnerDto owner;
	private ShelterDto shelter;
	private AdoptionPostingDto adoptionPosting;
	private PetProfileDto petProfile;
	private String type;

	public PetDto(Integer id) {
		this.id = id;
		this.type = "Koala-Hamster";
	}
	public PetDto(Integer id, String type) {
		this.id = id;
		this.type = type;
	}
	public PetDto(Integer id, String type, PetProfileDto description) {
		this.id = id;
		this.type = type;
		this.petProfile = description;
	}
	public PetDto(Integer id, String type, PetProfileDto description, OwnerDto owner) {
		this.id = id;
		this.owner = owner;
		this.type = type;
		this.petProfile = description;
	}
	public PetDto(Integer id, String type, PetProfileDto description, ShelterDto shelter) {
		this.id = id;
		this.shelter = shelter;
		this.type = type;
		this.petProfile = description;
	}
	public PetDto(Integer id, String type, PetProfileDto description, OwnerDto owner, AdoptionPostingDto posting) {
		this.id = id;
		this.owner = owner;
		this.type = type;
		this.petProfile = description;
		this.adoptionPosting = posting;
	}
	public PetDto(Integer id, String type, PetProfileDto description, ShelterDto shelter, AdoptionPostingDto posting) {
		this.id = id;
		this.shelter = shelter;
		this.type = type;
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
	public String getType() {
		return this.type;
	}
}
