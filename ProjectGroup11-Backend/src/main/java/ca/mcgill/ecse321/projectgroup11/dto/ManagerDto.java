package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class ManagerDto extends AccountUserDto {

	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!
	/* We wrote here all the possible ways to create an ManagerDto object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)

	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 */
	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress) {
		super(ID, firstName, lastName, emailAddress);
	}
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 */
	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		super(ID, firstName, lastName, emailAddress, phoneNumber);
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
	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description);
	}
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param phoneNumber
	 * @param description
	 * @param shelter
	 */

	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, ShelterDto shelter) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description);
		this.shelter = shelter;
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
	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, List<AddressDto> address) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
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
	 * @param shelter
	 */
	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, List<AddressDto> address, ShelterDto shelter) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
		this.shelter = shelter;
	}

	private ShelterDto shelter;

	public ShelterDto getShelterDto() {
		return this.shelter;
	}
}
