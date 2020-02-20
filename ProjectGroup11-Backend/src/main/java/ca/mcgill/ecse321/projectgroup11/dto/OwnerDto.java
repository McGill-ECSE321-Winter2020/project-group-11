package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.HashSet;
import java.util.Set;

public class OwnerDto extends AccountUserDto {
	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!

	public OwnerDto(Integer ID, String firstName, String lastName, String password) {
		super(ID, firstName, lastName, password);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, PetDto pet) {
		super(ID, firstName, lastName, password);
		HashSet<PetDto> pets = new HashSet<PetDto>();
		pets.add(pet);
		this.pet = pets;
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, AddressDto address, PetDto pet) {
		super(ID, firstName, lastName, password);
		HashSet<PetDto> pets = new HashSet<PetDto>();
		pets.add(pet);
		this.pet = pets;
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, AddressDto address) {
		super(ID, firstName, lastName, password);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, String emailAddress) {
		super(ID, firstName, lastName, password);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber) {
		super(ID, firstName, lastName, password);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description) {
		super(ID, firstName, lastName, password);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, PetDto pet) {
		super(ID, firstName, lastName, password);
		HashSet<PetDto> pets = new HashSet<PetDto>();
		pets.add(pet);
		this.pet = pets;
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address) {
		super(ID, firstName, lastName, password);
	}

	public OwnerDto(Integer ID, String firstName, String lastName, String password, String emailAddress,
			String phoneNumber, String description, AddressDto address, PetDto pet) {
		super(ID, firstName, lastName, password);
		HashSet<PetDto> pets = new HashSet<PetDto>();
		pets.add(pet);
		this.pet = pets;
	}

	private Set<PetDto> pet;

	public Set<PetDto> getPetDto() {
		return this.pet;
	}
}
