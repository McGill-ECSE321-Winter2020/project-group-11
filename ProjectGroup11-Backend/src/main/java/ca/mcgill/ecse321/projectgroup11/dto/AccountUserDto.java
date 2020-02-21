package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountUserDto {
	private Integer userID;

	private String firstName;
	private String lastName;

	private String phoneNumer;
	private String emailAddress;

	private String description;

	private List<AddressDto> address;

	public AccountUserDto(Integer ID, String firstName, String lastName) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = "Totally not a robot";
		this.phoneNumer = "999-999-9999";
		this.emailAddress = "emailme!:)@gmail.com";
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;

		this.description = "Totally not a robot";
		this.phoneNumer = "999-999-9999";
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;

		this.description = "Totally not a robot";
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;
		this.description = description;
	}

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
