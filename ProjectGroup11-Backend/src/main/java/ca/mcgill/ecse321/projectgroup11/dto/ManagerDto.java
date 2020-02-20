package ca.mcgill.ecse321.projectgroup11.dto;

public class ManagerDto extends AccountUserDto {

	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!

	public ManagerDto(Integer ID, String firstName, String lastName, String password) {
		super(ID, firstName, lastName, password);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, ShelterDto shelter) {
		super(ID, firstName, lastName, password);
		this.shelter = shelter;
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, AddressDto address,
			ShelterDto shelter) {
		super(ID, firstName, lastName, password);
		this.shelter = shelter;
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, AddressDto address) {
		super(ID, firstName, lastName, password);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, String emailAddress) {
		super(ID, firstName, lastName, password);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber) {
		super(ID, firstName, lastName, password);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description) {
		super(ID, firstName, lastName, password);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, ShelterDto shelter) {
		super(ID, firstName, lastName, password);
		this.shelter = shelter;
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address) {
		super(ID, firstName, lastName, password);
	}

	public ManagerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address, ShelterDto shelter) {
		super(ID, firstName, lastName, password);
		this.shelter = shelter;
	}

	private ShelterDto shelter;

	public ShelterDto getShelterDto() {
		return this.shelter;
	}
}
