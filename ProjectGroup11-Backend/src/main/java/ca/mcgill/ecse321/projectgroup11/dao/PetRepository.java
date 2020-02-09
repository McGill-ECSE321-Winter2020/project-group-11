package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.Pet;

public interface PetRepository extends CrudRepository<Pet, Integer>{

	Pet findPetById(Integer id);

}