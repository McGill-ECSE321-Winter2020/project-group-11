package ca.mcgill.ecse321.projectgroup11.javacode;

import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AccountUser {
	private Integer userID;

	public void setUserID(Integer value) {
		this.userID = value;
	}

	@Id
	public Integer getUserID() {
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

	private void setPassword(String value) {
		this.password = value;
	}

	private String getPassword() {
		return this.password;
	}

	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	private String phoneNumer;

	public void setPhoneNumer(String value) {
		this.phoneNumer = value;
	}

	public String getPhoneNumer() {
		return this.phoneNumer;
	}

	private String emailAddress;

	public void setEmailAddress(String value) {
		this.emailAddress = value;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	private String description;

	public void setDescription(String value) {
		this.description = value;
	}

	public String getDescription() {
		return this.description;
	}

	private Set<Address> address;

	@OneToMany
	public Set<Address> getAddress() {
		return this.address;
	}

	public void setAddress(Set<Address> addresss) {
		this.address = addresss;
	}

}
