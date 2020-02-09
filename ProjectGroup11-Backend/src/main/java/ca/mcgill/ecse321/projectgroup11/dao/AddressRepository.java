package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{

	Address findAddressById(Integer id);

}