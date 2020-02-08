package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public abstract class User {
	private Integer userID;

	public void setUserID(Integer value) {
		this.userID = value;
	}

	@Id
	private Integer getUserID() {
		return this.userID;
	}

	private String username;

	public void setUsername(String value) {
		this.username = value;
	}

	public String getUsername() {
		return this.username;
	}

	private String password;

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	public String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String phoneNumer;

	public void setPhoneNumer(String value) {
		this.phoneNumer = value;
	}

	public String getPhoneNumer() {
		return this.phoneNumer;
	}

	public String emailAddress;

	public void setEmailAddress(String value) {
		this.emailAddress = value;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public String description;

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	public Set<Address> address;

	@OneToMany
	public Set<Address> getAddress() {
		return this.address;
	}

	public void setAddress(Set<Address> addresss) {
		this.address = addresss;
	}

}
