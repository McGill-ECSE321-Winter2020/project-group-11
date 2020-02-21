package ca.mcgill.ecse321.projectgroup11.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup11.dao.AccountUserRepository;
import ca.mcgill.ecse321.projectgroup11.dao.PetsRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Address;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;

@Service
public class AccountUserService {
	
	@Autowired
	AccountUserRepository userRepo;
	
	@Autowired
	PetsRepository petRepo;
	
	
	@Transactional
	public List<AccountUser> getAllAccountUsers() {
		ArrayList<AccountUser> aUsers = new ArrayList<>();
		Iterable<AccountUser> iterUsers = userRepo.findAll();
		for(AccountUser user : iterUsers) {
			aUsers.add(user);
		}
		
		return aUsers;
	}
	
	@Transactional
	public AccountUser getAccountUserByID(Integer ID) {
		return userRepo.findAccountUserByuserID(ID);
	}
	
	@Transactional
	public List<AccountUser> getAccountUsersByName(String firstName, String lastName) {
		ArrayList<AccountUser> users = new ArrayList<>();
		Iterable<AccountUser> iterUsers = userRepo.findAll();
		for(AccountUser user : iterUsers) {
			if(user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
				users.add(user);
			}
		}
		
		return users;
	}
	
	@Transactional
	public List<AccountUser> getAccountUsersByEmail(String email) {
		ArrayList<AccountUser> users = new ArrayList<>();
		Iterable<AccountUser> iterUsers = userRepo.findAll();
		for(AccountUser user : iterUsers) {
			if(user.getEmailAddress().equals(email)) {
				users.add(user);
			}
		}
		
		return users;
	}
	
	@Transactional
	public AccountUser updateAccountUser(AccountUser a) {
		userRepo.save(a);
		return a;
	}
	
	
	
	@Transactional
	public Adopter createAdopter(String firstName, String lastName, String email, String phone, 
								 String password, String description, Address address,
								 Integer ID) {
		Adopter a = new Adopter();
		a.setFirstName(firstName);
		a.setLastName(lastName);
		
		HashSet<Address> addresses = new HashSet<>();
		addresses.add(address);
		a.setAddress(addresses);
		
		a.setEmailAddress(email);
		a.setPhoneNumer(phone);
		a.setPassword(password);
		a.setDescription(description);
		
		a.setUserID(ID);
		
		userRepo.save(a);
		
		return a;
	}
	
	@Transactional
	public Adopter updateAdopter(Adopter a) {
		userRepo.save(a);
		return a;
	}
	
	
	@Transactional
	public Adopter createAdopter(String firstName, String lastName, String password, Integer ID) {
		Adopter a = new Adopter();
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setPassword(password);
		
		a.setUserID(ID);
		userRepo.save(a);
		return a;
	}
	
	@Transactional
	public Adopter getAdopterByID(Integer id) {
		Adopter a = (Adopter) userRepo.findAccountUserByuserID(id);
		return a;
	}
	
	@Transactional
	public List<AccountUser> getAllAdopters() {
		//TODO: Check if these things work. if not we might need a query method... fun
		ArrayList<AccountUser> adopters = new ArrayList<>();
		Iterable<AccountUser> users = userRepo.findAll();
		for(AccountUser user : users) {
			if(user instanceof Adopter) adopters.add(user);
		}
		
		return adopters;
	}
	
	
	@Transactional
	public Owner updateOwner(Owner a) {
		userRepo.save(a);
		return a;
	}
	
	@Transactional
	public Owner createOwner(String firstName, String lastName, String email, String phone, 
							 String password, String description, Address address,
							 Integer ID, Pet pet) {
		Owner o = new Owner();
		o.setFirstName(firstName);
		o.setLastName(lastName);
		
		HashSet<Address> addresses = new HashSet<>();
		addresses.add(address);
		o.setAddress(addresses);;
		
		o.setEmailAddress(email);
		o.setPhoneNumer(phone);
		o.setPassword(password);
		o.setDescription(description);
		
		o.setUserID(ID);
		
		HashSet<Pet> pets = new HashSet<>();
		pets.add(pet);
		o.setPet(pets);
		
		
		userRepo.save(o);
		
		return o;
	}
	
	
	@Transactional
	public Owner createOwner(String firstName, String lastName, String password, Integer ID) {
		Owner o = new Owner();
		o.setFirstName(firstName);
		o.setLastName(lastName);
		o.setPassword(password);
		
		o.setUserID(ID);
		
		//HashSet<Pet> pets = new HashSet<>();
		//o.setPet(pets);

		userRepo.save(o);
		
		return o;
	}
	
	@Transactional
	public Owner createOwner(String firstName, String lastName, String password, Integer ID, Pet pet) {
		Owner o = new Owner();
		o.setFirstName(firstName);
		o.setLastName(lastName);
		o.setPassword(password);
		
		o.setUserID(ID);
		
		HashSet<Pet> pets = new HashSet<>();
		pets.add(pet);
		o.setPet(pets);
		

		userRepo.save(o);
		
		return o;
	}
	
	
	@Transactional
	public Owner createOwner(String firstName, String lastName, String email, String phone, 
							 String password, String description, Address address,
							 Integer ID, Integer petID, PetProfile p) {
		Owner o = new Owner();
		o.setFirstName(firstName);
		o.setLastName(lastName);
		
		HashSet<Address> addresses = new HashSet<>();
		addresses.add(address);
		o.setAddress(addresses);;
		
		o.setEmailAddress(email);
		o.setPhoneNumer(phone);
		o.setPassword(password);
		o.setDescription(description);
		
		o.setUserID(ID);
		
		HashSet<Pet> pets = new HashSet<>();
		Pet pet = new Pet();
		pet.setId(petID);
		pet.setOwner(o);
		
		pets.add(pet);
		o.setPet(pets);
		
		petRepo.save(pet, p);
		userRepo.save(o);
		
		return o;
	}
	
	@Transactional
	public Owner createOwner(String firstName, String lastName, String password, Integer ID, Integer petID, PetProfile p) {
		Owner o = new Owner();
		o.setFirstName(firstName);
		o.setLastName(lastName);
		
		HashSet<Address> addresses = new HashSet<>();
		o.setAddress(addresses);;

		o.setPassword(password);
		
		o.setUserID(ID);
		
		HashSet<Pet> pets = new HashSet<>();
		Pet pet = new Pet();
		pet.setId(petID);
		pet.setOwner(o);
		
		pets.add(pet);
		o.setPet(pets);
		
		petRepo.save(pet, p);
		userRepo.save(o);
		
		return o;
	}
	
	
	
	
	@Transactional
	public Owner getOwnerByID(Integer id) {
		Owner o = (Owner) userRepo.findAccountUserByuserID(id);
		return o;
	}
	@Transactional
	public List<AccountUser> getAllOwners() {
		ArrayList<AccountUser> owners = new ArrayList<>();
		Iterable<AccountUser> users = userRepo.findAll();
		for(AccountUser user : users) {
			if(user instanceof Owner) owners.add(user);
		}
		
		return owners;
	}
	
	@Transactional
	public Manager updateManager(Manager a) {
		userRepo.save(a);
		return a;
	}
	
	@Transactional
	public Manager createManager(String firstName, String lastName, String email, String phone, 
							     String password, String description, Address address,
							     Integer ID, Shelter shelter) {
		Manager m = new Manager();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		
		HashSet<Address> addresses = new HashSet<>();
		addresses.add(address);
		m.setAddress(addresses);;
		
		m.setEmailAddress(email);
		m.setPhoneNumer(phone);
		m.setPassword(password);
		m.setDescription(description);
		
		m.setUserID(ID);
		
		m.setShelter(shelter);
		
		
		userRepo.save(m);
		
		return m;
	}
	
	@Transactional
	public Manager createManager(String firstName, String lastName, String password, Integer ID) {
		Manager m = new Manager();
		m.setFirstName(firstName);
		m.setLastName(lastName);
		
		HashSet<Address> addresses = new HashSet<>();
		m.setAddress(addresses);
		
		m.setPassword(password);
		
		m.setUserID(ID);
		
		userRepo.save(m);
		return m;
	}
	
	@Transactional
	public Manager getManagerByID(Integer id) {
		Manager m = (Manager) userRepo.findAccountUserByuserID(id);
		return m;
	}
	
	@Transactional
	public List<AccountUser> getAllManagers() {
		ArrayList<AccountUser> managers = new ArrayList<>();
		Iterable<AccountUser> users = userRepo.findAll();
		for(AccountUser user : users) {
			if(user instanceof Manager) managers.add(user);
		}
		
		return managers;
	}
}
