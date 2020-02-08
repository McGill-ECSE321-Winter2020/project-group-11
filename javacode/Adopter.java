import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Adopter extends User {
	private AdoptionPosting adoptionPosting;

	@ManyToOne
	public AdoptionPosting getAdoptionPosting() {
		return this.adoptionPosting;
	}

	public void setAdoptionPosting(AdoptionPosting adoptionPosting) {
		this.adoptionPosting = adoptionPosting;
	}

	private Integer adopterID;

	private void setAdopterID(Integer value) {
		this.adopterID = value;
	}

	@Id
	private Integer getAdopterID() {
		return this.adopterID;
	}
}
