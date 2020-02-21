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
		return addRepo.findAddressById(ID);
	}
	
	@Transactional
	public Address createAddress(Integer ID, Integer streetNum, String street, String city, String province, String postal) {
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
		return addRepo.save(address);
	}
}
