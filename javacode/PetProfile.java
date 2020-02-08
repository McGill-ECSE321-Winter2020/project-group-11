import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PetProfile {
	private String description;

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	private String photoURL;

	private void setPhotoURL(String value) {
		this.photoURL = value;
	}

	private String getPhotoURL() {
		return this.photoURL;
	}

	private Integer id;

	private void setId(Integer value) {
		this.id = value;
	}

	@Id
	private Integer getId() {
		return this.id;
	}

	private String name;

	private void setName(String value) {
		this.name = value;
	}

	private String getName() {
		return this.name;
	}
}
