package ca.mcgill.ecse321.projectgroup11.javacode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
	private String street;

	public void setStreet(String value) {
		this.street = value;
	}

	public String getStreet() {
		return this.street;
	}

	private Integer streetNumber;

	public void setStreetNumber(Integer value) {
		this.streetNumber = value;
	}

	public Integer getStreetNumber() {
		return this.streetNumber;
	}

	private String city;

	public void setCity(String value) {
		this.city = value;
	}

	public String getCity() {
		return this.city;
	}

	private String province;

	public void setProvince(String value) {
		this.province = value;
	}

	public String getProvince() {
		return this.province;
	}

	private String postalCode;

	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	private Integer id;

<<<<<<< HEAD
	public void setId(String value) {
=======
	public void setId(Integer value) {
>>>>>>> branch 'master' of https://github.com/McGill-ECSE321-Winter2020/project-group-11.git
		this.id = value;
	}

	@Id
<<<<<<< HEAD
	public String getId() {
=======
	public Integer getId() {
>>>>>>> branch 'master' of https://github.com/McGill-ECSE321-Winter2020/project-group-11.git
		return this.id;
	}
}
