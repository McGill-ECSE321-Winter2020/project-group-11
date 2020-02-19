package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends AccountUser {
	private Shelter shelter;

	@OneToOne(mappedBy = "manager", optional = false)
	public Shelter getShelter() {
		return this.shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

}
