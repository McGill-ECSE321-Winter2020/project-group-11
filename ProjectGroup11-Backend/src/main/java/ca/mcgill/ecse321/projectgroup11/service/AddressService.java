package ca.mcgill.ecse321.projectgroup11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup11.dao.AddressRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.Address;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addRepo;
	
	
	@Transactional
	public List<Address> getAllAddresses() {
		ArrayList<Address> adds = new ArrayList<>();
		Iterable<Address> iterAdds = addRepo.findAll();
		for(Address addr : iterAdds) {
			adds.add(addr);
		}
		
		return adds;
	}
	
	@Transactional
	public Address getAddressById(Integer ID) {
		if(ID == null) return null;
		return addRepo.findAddressById(ID);
	}
	
	@Transactional
	public Address createAddress(Integer ID, Integer streetNum, String street, String city, String province, String postal) {
		
		if(street == null || !street.matches("[a-zA-Z ]+")) {
			throw new IllegalArgumentException("Invalid Street - not alphabetic");
		}
		int spaceIndex = street.indexOf(' ');
		if(spaceIndex == -1 || spaceIndex == 0 || 
		  spaceIndex == street.length() - 1 || spaceIndex != street.lastIndexOf(' ')) {
			throw new IllegalArgumentException("Invalid Street - must have at least two parts (include Street/Circle/etc.)");
		}
		
		if(city == null || !city.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Invalid City Name - not alphabetic");
		}
		if(province == null || !province.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Invalid Province - not alphabetic");
		}
		if(streetNum == null || streetNum <1) {  
			throw new IllegalArgumentException("Street  Number < 1?");
		}
		
		Address add = new Address();
		add.setId(ID);
		add.setCity(city);
		add.setPostalCode(postal);
		add.setProvince(province);
		add.setStreet(street);
		add.setStreetNumber(streetNum);
		
		return addRepo.save(add);
	}
	
	@Transactional
	public Address updateAddress(Address address) {
		if(address == null || this.getAddressById(address.getId()) == null) {
			throw new IllegalArgumentException("Cannot update address that is not in the database");
		}
		return addRepo.save(address);
	}
}
