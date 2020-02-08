package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	private String street;

	private void setStreet(String value) {
		this.street = value;
	}

	private String getStreet() {
		return this.street;
	}

	private Integer streetNumber;

	private void setStreetNumber(Integer value) {
		this.streetNumber = value;
	}

	private Integer getStreetNumber() {
		return this.streetNumber;
	}

	private String city;

	private void setCity(String value) {
		this.city = value;
	}

	private String getCity() {
		return this.city;
	}

	private String province;

	private void setProvince(String value) {
		this.province = value;
	}

	private String getProvince() {
		return this.province;
	}

	private String postalCode;

	private void setPostalCode(String value) {
		this.postalCode = value;
	}

	private String getPostalCode() {
		return this.postalCode;
	}

	private String id;

	private void setId(String value) {
		this.id = value;
	}

	@Id
	private String getId() {
		return this.id;
	}
}
