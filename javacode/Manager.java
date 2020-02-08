import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Manager extends User {
	private Shelter shelter;

	@OneToOne(mappedBy = "manager", optional = false)
	public Shelter getShelter() {
		return this.shelter;
	}

	public void setShelter(Shelter shelter) {
		this.shelter = shelter;
	}

	private Integer managerID;

	private void setManagerID(Integer value) {
		this.managerID = value;
	}

	@Id
	private Integer getManagerID() {
		return this.managerID;
	}
}
