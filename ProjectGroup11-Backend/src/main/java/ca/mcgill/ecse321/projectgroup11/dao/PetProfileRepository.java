package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;

public interface PetProfileRepository extends CrudRepository<PetProfile, Integer>{

	PetProfile findPetProfileById(Integer id);

}