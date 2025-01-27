package ca.mcgill.ecse321.projectgroup11.javacode;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AdoptionPosting {
	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	private Set<Adopter> adopters;

	@OneToMany(mappedBy = "adoptionPosting")
	public Set<Adopter> getAdopters() {
		return this.adopters;
	}

	public void setAdopters(Set<Adopter> adopterss) {
		this.adopters = adopterss;
	}

	private Pet pet;


	@OneToOne 
	(mappedBy = "adoptionPosting", optional = true)
	public Pet getPet() {
		return this.pet;
	}
	

	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
