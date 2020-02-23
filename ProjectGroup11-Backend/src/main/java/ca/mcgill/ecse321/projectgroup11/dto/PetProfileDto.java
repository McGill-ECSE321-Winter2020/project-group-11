package ca.mcgill.ecse321.projectgroup11.dto;

public class PetProfileDto {
	private String name;
	private String type;
	private String description;
	private String photoURL;
	private String breed;
	private Boolean apartment;
	private Boolean kidsOkay;
	private Boolean petsOkay;
	private Boolean highEnergy;
	private String healthConcerns;
	private Integer id;
	
	// Given the logic of the constructors, a PetProfile object can have id name and type OR must specify all the more exhaustive fields (kids okay apartment etc)
	
	/**
	 * 
	 * @param ID
	 * @param name
	 * @param type
	 */
	public PetProfileDto(Integer ID, String name, String type) {
		//RIP - wish I knew this was possible before making AccountUser
		this(ID, name, type, null, null, null, null, null, null, null, null);
	}
	/**
	 * 
	 * @param ID
	 * @param name
	 * @param type
	 * @param description
	 * @param photoURL
	 * @param breed
	 * @param apartment
	 * @param kidsOk
	 * @param petsOk
	 * @param highEnergy
	 * @param healthConcerns
	 */
	public PetProfileDto(Integer ID, String name, String type, String description, 
						 String photoURL,String breed, Boolean apartment, Boolean kidsOk, 
						 Boolean petsOk, Boolean highEnergy, String healthConcerns) {
		this.id = ID;
		this.name = name;
		if(type == null) this.type = "Dog";
		else this.type = type;
		if(description == null) this.description = "A default description for pets.";
		else this.description = description;
		if(photoURL == null) this.photoURL = "abc/something.png";
		else this.photoURL = photoURL;
		if(breed == null) this.breed = "No breed specified";
		else this.breed = breed;
		if(apartment == null) this.apartment = false;
		else this.apartment = apartment;
		if(kidsOk == null) this.kidsOkay = false;
		else this.kidsOkay = kidsOk;
		if(petsOk == null) this.petsOkay = false;
		else this.petsOkay = petsOk;
		if(highEnergy == null) this.highEnergy = false;
		else this.apartment = apartment;
		if(healthConcerns == null) this.healthConcerns = "No health concerns";
		else this.healthConcerns = healthConcerns;
		
	}
	

	public String getType() {
		return this.type;
	}
	public String getDescription() {
		return this.description;
	}
	public String getPhotoURL() {
		return this.photoURL;
	}
	public String getBreed() {
		return this.breed;
	}
	public Boolean getApartment() {
		return this.apartment;
	}
	public Boolean getKidsOkay() {
		return this.kidsOkay;
	}
	public Boolean getPetsOkay() {
		return this.petsOkay;
	}
	public Boolean getHighEnergy() {
		return this.highEnergy;
	}
	public String getHealthConcerns() {
		return this.healthConcerns;
	}
	public Integer getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
}
