package ca.mcgill.ecse321.projectgroup11.javacode;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Owner")
public class Owner extends AccountUser {
	private Set<Pet> pet;

	@OneToMany(mappedBy = "owner")
	public Set<Pet> getPet() {
		return this.pet;
	}

	public void setPet(Set<Pet> pets) {
		this.pet = pets;
	}

}
