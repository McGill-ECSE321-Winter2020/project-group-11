package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class ManagerDto extends AccountUserDto {

	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!

	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress) {
		super(ID, firstName, lastName, emailAddress);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		super(ID, firstName, lastName, emailAddress, phoneNumber);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, ShelterDto shelter) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description);
		this.shelter = shelter;
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, List<AddressDto> address) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
	}

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
