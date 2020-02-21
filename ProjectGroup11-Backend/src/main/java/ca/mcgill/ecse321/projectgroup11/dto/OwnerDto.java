package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class OwnerDto extends AccountUserDto {
	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!

	public OwnerDto(Integer ID, String firstName, String lastName) {
		super(ID, firstName, lastName);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, List<PetDto> pets) {
		super(ID, firstName, lastName);
		this.pets = pets;
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String emailAddress) {
		super(ID, firstName, lastName, emailAddress);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String emailAddress,
					String phoneNumber) {
		super(ID, firstName, lastName, emailAddress, phoneNumber);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String emailAddress,
					String phoneNumber, String description) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String emailAddress,
					String phoneNumber, String description, List<AddressDto> address) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String emailAddress,
					String phoneNumber, String description, List<AddressDto> address, List<PetDto> pets) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
		this.pets = pets;
	}

	private List<PetDto> pets;

	public List<PetDto> getPetDto() {
		return this.pets;
	}
}
