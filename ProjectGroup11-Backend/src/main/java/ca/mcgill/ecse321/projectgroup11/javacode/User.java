import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public abstract class User {
	private Integer userID;

	private void setUserID(Integer value) {
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

	private void setPassword(String value) {
		this.password = value;
	}

	private String getPassword() {
		return this.password;
	}

	private String firstName;

	private void setFirstName(String value) {
		this.firstName = value;
	}

	private String getFirstName() {
		return this.firstName;
	}

	private String lastName;

	private void setLastName(String value) {
		this.lastName = value;
	}

	private String getLastName() {
		return this.lastName;
	}

	private String phoneNumer;

	private void setPhoneNumer(String value) {
		this.phoneNumer = value;
	}

	private String getPhoneNumer() {
		return this.phoneNumer;
	}

	private String emailAddress;

	private void setEmailAddress(String value) {
		this.emailAddress = value;
	}

	private String getEmailAddress() {
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
