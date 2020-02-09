package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;

public interface AdoptionPostingRepository extends CrudRepository<AdoptionPosting, Integer>{

	AdoptionPosting findAdoptionPostingById(Integer id);

}