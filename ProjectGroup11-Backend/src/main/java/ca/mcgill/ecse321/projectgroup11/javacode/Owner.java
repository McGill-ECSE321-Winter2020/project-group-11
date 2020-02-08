package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Owner extends User {
	private Set<Pet> pet;

	@OneToMany(mappedBy = "owner")
	public Set<Pet> getPet() {
		return this.pet;
	}

	public void setPet(Set<Pet> pets) {
		this.pet = pets;
	}

}
