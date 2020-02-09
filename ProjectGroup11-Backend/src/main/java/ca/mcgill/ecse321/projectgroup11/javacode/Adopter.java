package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Adopter extends AccountUser {
	private AdoptionPosting adoptionPosting;

	@ManyToOne
	public AdoptionPosting getAdoptionPosting() {
		return this.adoptionPosting;
	}

	public void setAdoptionPosting(AdoptionPosting adoptionPosting) {
		this.adoptionPosting = adoptionPosting;
	}

}
