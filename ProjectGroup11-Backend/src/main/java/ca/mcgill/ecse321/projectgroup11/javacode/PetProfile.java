package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PetProfile {
	private String description;

	public void setDescription(String value) {
		this.description = value;
	}

	
	public String getDescription() {
		return this.description;
	}

	private String photoURL;

	public void setPhotoURL(String value) {
		this.photoURL = value;
	}

	public String getPhotoURL() {
		return this.photoURL;
	}
	
	private String breed;

	public void setBreed(String value) {
		this.breed = value;
	}

	public String getBreed() {
		return this.breed;
	}
	
	private Boolean apartment;

	public void setApartment(Boolean value) {
		this.apartment = value;
	}

	public Boolean getApartment() {
		return this.apartment;
	}
	
	private Boolean kidOkay;

	public void setKidOkay(Boolean value) {
		this.kidOkay = value;
	}

	public Boolean getKidOkay() {
		return this.kidOkay;
	}

	private String petsOkay;

	public void setPetsOkay(String value) {
		this.petsOkay = value;
	}

	public String getPetsOkay() {
		return this.petsOkay;
	}
	
	private Boolean highEnergy;

	public void setHighEnergy(Boolean value) {
		this.highEnergy = value;
	}

	public Boolean getHighEnergy() {
		return this.highEnergy;
	}
	
	private String healthConcerns;

	public void setHealthConcerns(String value) {
		this.healthConcerns = value;
	}

	public String getHealthConcerns() {
		return this.healthConcerns;
	}
	
	
	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

}
