package ca.mcgill.ecse321.projectgroup11.javacode;

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

	public void setPhotoURL(String value) {
		this.photoURL = value;
	}

	public String getPhotoURL() {
		return this.photoURL;
	}

	private Integer id;

	public void setId(Integer value) {
		this.id = value;
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

}
