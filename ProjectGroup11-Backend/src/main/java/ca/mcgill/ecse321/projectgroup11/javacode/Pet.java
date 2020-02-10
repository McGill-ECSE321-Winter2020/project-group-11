package ca.mcgill.ecse321.projectgroup11.javacode;

import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Pet {
	private Owner owner;

	@ManyToOne
	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	private Shelter shelter;

	@ManyToOne
	public Shelter getShelter() {
		return this.shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	private AdoptionPosting adoptionPosting;

	@OneToOne(optional = true)
	public AdoptionPosting getAdoptionPosting() {
		return this.adoptionPosting;
	}

	public void setAdoptionPosting(AdoptionPosting adoptionPosting) {
		this.adoptionPosting = adoptionPosting;
	}

	private PetProfile petProfile;

	@OneToOne(optional = true)
	public PetProfile getPetProfile() {
		return this.petProfile;
	}

	public void setPetProfile(PetProfile petProfile) {
		this.petProfile = petProfile;
	}

	private String type;

	public void setType(String value) {
		this.type = value;
	}

	public String getType() {
		return this.type;
	}
}
