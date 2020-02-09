package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;

public interface ShelterRepository extends CrudRepository<Shelter, Integer>{

	Shelter findShelterById(Integer id);

}