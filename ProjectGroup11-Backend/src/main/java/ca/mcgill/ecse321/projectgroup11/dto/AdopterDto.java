package ca.mcgill.ecse321.projectgroup11.dto;

public class AdopterDto extends AccountUserDto {
	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!

	public AdopterDto(Integer ID, String firstName, String lastName, String password) {
		super(ID, firstName, lastName, password);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, AdoptionPostingDto adopt) {
		super(ID, firstName, lastName, password);
		this.adoptionPosting = adopt;
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, AddressDto address,
			AdoptionPostingDto adopt) {
		super(ID, firstName, lastName, password);
		this.adoptionPosting = adopt;
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, AddressDto address) {
		super(ID, firstName, lastName, password);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, String emailAddress) {
		super(ID, firstName, lastName, password);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber) {
		super(ID, firstName, lastName, password);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description) {
		super(ID, firstName, lastName, password);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AdoptionPostingDto adopt) {
		super(ID, firstName, lastName, password);
		this.adoptionPosting = adopt;
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address) {
		super(ID, firstName, lastName, password);
	}

	public AdopterDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address, AdoptionPostingDto adopt) {
		super(ID, firstName, lastName, password);
		this.adoptionPosting = adopt;
	}

	private AdoptionPostingDto adoptionPosting;

	public AdoptionPostingDto getAdoptionPostingDto() {
		return this.adoptionPosting;
	}

}
