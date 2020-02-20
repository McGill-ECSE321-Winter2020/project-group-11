package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.HashSet;
import java.util.Set;

public class AccountUserDto {
	private Integer userID;
	private String password;

	private String firstName;
	private String lastName;

	private String phoneNumer;
	private String emailAddress;

	private String description;

	private Set<AddressDto> address;

	public AccountUserDto(Integer ID, String firstName, String lastName, String password) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

		this.description = "Totally not a robot";
		this.phoneNumer = "999-999-9999";
		this.emailAddress = "emailme!:)@gmail.com";
		/*
		AddressDto addDat = new AddressDto();
		HashSet<AddressDto> temp = new HashSet<>();
		temp.add(addDat);
		*/
		//address = temp;
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String password, AddressDto address) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

		this.description = "Totally not a robot";
		this.phoneNumer = "999-999-9999";
		this.emailAddress = "emailme!:)@gmail.com";
		/*
		HashSet<AddressDto> temp = new HashSet<>();
		temp.add(address);
		this.address = temp;
		*/
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String password, String emailAddress) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailAddress = emailAddress;

		this.description = "Totally not a robot";
		this.phoneNumer = "999-999-9999";

		/*
		AddressDto addDat = new AddressDto();
		HashSet<AddressDto> temp = new HashSet<>();
		temp.add(addDat);
		address = temp;
		*/
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;

		this.description = "Totally not a robot";

		/*
		AddressDto addDat = new AddressDto();
		HashSet<AddressDto> temp = new HashSet<>();
		temp.add(addDat);
		address = temp;
		*/
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;
		this.description = description;

		/*
		AddressDto addDat = new AddressDto();
		HashSet<AddressDto> temp = new HashSet<>();
		temp.add(addDat);
		address = temp;
		*/
	}

	public AccountUserDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address) {
		this.userID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.phoneNumer = phoneNumber;
		this.description = description;
		HashSet<AddressDto> temp = new HashSet<>();
		temp.add(address);
		this.address = temp;
	}

	public Integer getUserID() {
		return this.userID;
	}

	public String getPassword() {
		return this.password;
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

	public Set<AddressDto> getAddressDto() {
		return this.address;
	}

}
