package ca.mcgill.ecse321.projectgroup11.dto;

public class PetDto {
	private Integer id;
	private OwnerDto owner;
	private ShelterDto shelter;
	private AdoptionPostingDto adoptionPosting;
	private PetProfileDto petProfile;
	/* We wrote here all the possible ways to create an PetDto object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)

	
	/**
	 * 
	 * @param id
	 */
	public PetDto(Integer id) {
		this.id = id;
	}
	/**
	 * 
	 * @param id
	 * @param description
	 */
	public PetDto(Integer id, PetProfileDto description) {
		this.id = id;
		this.petProfile = description;
	}
	/**
	 * 
	 * @param id
	 * @param description
	 * @param owner
	 */
	public PetDto(Integer id, PetProfileDto description, OwnerDto owner) {
		this.id = id;
		this.owner = owner;
		this.petProfile = description;
	}
	/**
	 * 
	 * @param id
	 * @param description
	 * @param shelter
	 */
	public PetDto(Integer id, PetProfileDto description, ShelterDto shelter) {
		this.id = id;
		this.shelter = shelter;
		this.petProfile = description;
	}
	/**
	 * 
	 * @param id
	 * @param description
	 * @param owner
	 * @param posting
	 */
	public PetDto(Integer id, PetProfileDto description, OwnerDto owner, AdoptionPostingDto posting) {
		this.id = id;
		this.owner = owner;
		this.petProfile = description;
		this.adoptionPosting = posting;
	}
	/**
	 * 
	 * @param id
	 * @param description
	 * @param shelter
	 * @param posting
	 */
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
