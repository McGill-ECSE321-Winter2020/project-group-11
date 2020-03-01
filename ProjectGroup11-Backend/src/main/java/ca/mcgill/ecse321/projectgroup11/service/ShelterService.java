package ca.mcgill.ecse321.projectgroup11.service;

import ca.mcgill.ecse321.projectgroup11.dao.ShelterRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShelterService {
    @Autowired
    ShelterRepository shelterRepo;
    @Autowired
    AccountUserService userService;

    @Transactional
    public List<Shelter> getAllShelters() {
        ArrayList<Shelter> shelters = new ArrayList<>();
        Iterable<Shelter> iterShel = shelterRepo.findAll();

        //Convert to array list
        for(Shelter s : iterShel) {
            shelters.add(s);
        }

        return shelters;
    }


    @Transactional
    public Shelter getShelterById(Integer ID) {
        if(ID == null) return null;
        return shelterRepo.findShelterById(ID);
    }

    @Transactional
    public Shelter createShelter(Integer ID, List<Pet> pets, Manager manager) {

        Shelter s = new Shelter();
        if(shelterRepo.findShelterById(ID) != null && shelterRepo.findShelterById(ID).getId() == ID) {
            throw new IllegalArgumentException("Shelter with this ID already exists");
        }
        s.setId(ID);
        if(pets != null) {
            if(pets.size() == 0) {
                throw new IllegalArgumentException("Please do not create an empty set of pets");
            }
            Set<Pet> petsList = new HashSet<>();
            petsList.addAll(pets);
            s.setPet(petsList);
        }
        if(manager != null) {
            try   {
                Manager a = (Manager) userService.getManagerByID(manager.getUserID());
            }
            catch(Exception e) {
                if(e.getMessage() == null) {
                    throw new IllegalArgumentException("Manager must be saved first");
                }
            }
            s.setManager(manager);



            s.setManager(manager);
        }

        return shelterRepo.save(s);
    }
    @Transactional
    public Shelter createShelter(Integer ID) {

        Shelter s = new Shelter();
        if( shelterRepo.findShelterById(ID) != null && shelterRepo.findShelterById(ID).getId() == ID) {
            throw new IllegalArgumentException("Shelter with this ID already exists");
        }
        s.setId(ID);

        return shelterRepo.save(s);
    }

    @Transactional
    public Shelter updateShelter(Shelter s) {
        if(s == null || this.getShelterById(s.getId()) == null) {
            throw new IllegalArgumentException("Cannot update shelter that is not in the database");
        }
        return shelterRepo.save(s);
    }
}
