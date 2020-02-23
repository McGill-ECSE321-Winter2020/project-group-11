package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class AdopterDto extends AccountUserDto {
	// A LOT of constructors... I may have gone overboard. But there's just so many
	// Attributes!
	/* We wrote here all the possible ways to create an AdopterDto object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)
	 * 
	 * 
	 */
	
	
	/**
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 */
	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress) {
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
	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
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

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
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
	 * @param address
	 */

	public AdopterDto(Integer ID, String firstName, String lastName, String emailAddress,
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
	 * @param adopt
	 */

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
