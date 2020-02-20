package ca.mcgill.ecse321.projectgroup11.dto;

public class AddressDto {
	private Integer streetNumber;
	private String street;
	private String city;
	private String province;
	private String postalCode;
	private Integer id;

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
