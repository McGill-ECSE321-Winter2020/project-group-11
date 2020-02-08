package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Shelter {
	private Set<Pet> pet;

	@OneToMany(mappedBy = "shelter")
	public Set<Pet> getPet() {
		return this.pet;
	}

	public void setPet(Set<Pet> pets) {
		this.pet = pets;
	}

	private Manager manager;

	@OneToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	private Integer id;

	private void setId(Integer value) {
		this.id = value;
	}

	@Id
	private Integer getId() {
		return this.id;
	}
}
