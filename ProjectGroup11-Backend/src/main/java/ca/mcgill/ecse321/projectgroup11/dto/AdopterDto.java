package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class AdopterDto extends AccountUserDto {
	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!

	public AdopterDto(Integer ID, String firstName, String lastName) {
		super(ID, firstName, lastName);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress) {
		super(ID, firstName, lastName, emailAddress);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber) {
		super(ID, firstName, lastName, emailAddress, phoneNumber);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, List<AddressDto> address) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
			String phoneNumber, String description, List<AddressDto> address, AdoptionPostingDto adopt) {
		super(ID, firstName, lastName, emailAddress, phoneNumber, description, address);
		this.adoptionPosting = adopt;
	}

	private AdoptionPostingDto adoptionPosting;

	public AdoptionPostingDto getAdoptionPostingDto() {
		return this.adoptionPosting;
	}

}
