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

	private Integer ownerID;

	private void setOwnerID(Integer value) {
		this.ownerID = value;
	}

	@Id
	private Integer getOwnerID() {
		return this.ownerID;
	}
}
