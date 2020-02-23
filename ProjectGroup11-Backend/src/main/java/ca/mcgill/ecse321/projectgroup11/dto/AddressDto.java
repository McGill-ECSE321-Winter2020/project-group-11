package ca.mcgill.ecse321.projectgroup11.dto;

public class AddressDto {
	private Integer streetNumber;
	private String street;
	private String city;
	private String province;
	private String postalCode;
	private Integer id;
	
	/* We wrote here all the possible ways to create an AddressDto object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)

	/**
	 * 
	 * @param id
	 * @param streetNum
	 * @param street
	 * @param city
	 * @param province
	 * @param postal
	 */
	public AddressDto(Integer id, Integer streetNum, String street, String city, String province, String postal) {
		this.street = street;
		this.streetNumber = streetNum;
		this.id = id;
		this.city = city;
		this.province = province;
		this.postalCode = postal;
	}

	public String getStreet() {
		return this.street;
	}
	public Integer getStreetNumber() {
		return this.streetNumber;
	}
	public String getCity() {
		return this.city;
	}
	public String getProvince() {
		return this.province;
	}
	public String getPostalCode() {
		return this.postalCode;
	}
	public Integer getId() {
		return this.id;
	}
}
