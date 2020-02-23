package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class AccountUserDto {
	private Integer userID;

	private String firstName;
	private String lastName;

	private String phoneNumer;
	private String emailAddress;

	private String description;

	private List<AddressDto> address;
	/* We wrote here all the possible ways to create an AdopterDto object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)
		Also, we have here that if the AccountUser is created without description or phoneNumer it is set to a default one (look inside the constructor method)
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 */
	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;

		this.description = "Totally not a robot";
		this.phoneNumer = "999-999-9999";
	}
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 */
	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;

		this.description = "Totally not a robot";
	}
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param description
	 */
	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;
		this.description = description;
	}
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param description
	 * @param address
	 */
	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, List<AddressDto> address) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;
		this.description = description;
		this.address = address;
	}

	public Integer getUserID() {
		return this.userID;
	}
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getPhoneNumer() {
		return this.phoneNumer;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public String getDescription() {
		return this.description;
	}

	public List<AddressDto> getAddressDto() {
		return this.address;
	}

}
