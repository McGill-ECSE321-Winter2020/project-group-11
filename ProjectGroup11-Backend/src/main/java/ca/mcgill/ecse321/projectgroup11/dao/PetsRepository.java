package ca.mcgill.ecse321.projectgroup11.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;

@Repository
public class PetsRepository {
	@Autowired
	EntityManager entityManager;

	@Transactional
	public Pet createPet(Integer i, Integer adoptID, String type) {
		PetProfile pp = new PetProfile();
		pp.setName("Hiya Name");
		pp.setApartment(true);
		pp.setDescription("The rest of this stuff should be null");
		pp.setId(10);
		
		Pet p = new Pet();
		p.setId(i);
		p.setType(type);
		p.setPetProfile(pp);
		
		AdoptionPosting a = new AdoptionPosting();
		a.setId(adoptID);
		
		
		p.setAdoptionPosting(a);
		a.setPet(p);
		
		entityManager.persist(pp);
		entityManager.persist(a);
		entityManager.persist(p);
		
		
		
		
		return p;
	}

	@Transactional
	public Pet getPet(Integer i) {
		Pet p = entityManager.find(Pet.class, i);
		return p;
	}
	
	@Transactional
	public void removePet(Integer id) {
		entityManager.remove(entityManager.find(Pet.class, id));
	}
	
	@Transactional
	public void clear() {
			entityManager.createQuery(
				      "DELETE FROM Pet").executeUpdate();
			entityManager.createQuery(
				      "DELETE FROM AdoptionPosting").executeUpdate();
			entityManager.createQuery(
				      "DELETE FROM PetProfile").executeUpdate();

	}

}