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
		if(ID == null) return null;
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
		if(a == null || this.getAccountUserByID(a.getUserID()) == null) {
			throw new IllegalArgumentException("Cannot update Account User that is not in the database");
		}
		userRepo.save(a);
		return a;
	}
	
	
	
	@Transactional
	public Adopter createAdopter(String name, String email, String phone, 
								 String password, String description, Address address,
								 Integer ID) {

		


		int space = name.indexOf(' ');
		
		Adopter a = new Adopter();
		
		a.setFirstName(name.substring(0, space));
		a.setLastName(name.substring(space+1));
		
		if(address != null) {
			HashSet<Address> addresses = new HashSet<>();
			addresses.add(address);
			a.setAddress(addresses);
		}
		
		
		a.setEmailAddress(email);
		a.setPhoneNumer(phone);
		a.setPassword(password);
		a.setDescription(description);
		
		a.setUserID(ID);
		
		userRepo.save(a);
		
		return a;
	}
	
	@Transactional
	public Adopter createAdopter(String name, String email, String password, Integer ID) {
		
		validateAccountUser(name, email, null, password, null, ID);
		
		int space = name.indexOf(' ');
		
		Adopter a = new Adopter();
		
		a.setFirstName(name.substring(0, space));
		a.setLastName(name.substring(space+1));
		a.setPassword(password);
		
		a.setUserID(ID);
		a.setEmailAddress(email);
		userRepo.save(a);
		return a;
	}
	
	@Transactional
	public Adopter updateAdopter(Adopter a) {
		if(a == null || this.getAdopterByID(a.getUserID()) == null) {
			throw new IllegalArgumentException("Cannot update Adopter that is not in the database");
		}
		userRepo.save(a);
		return a;
	}
	
	
	
	
	@Transactional
	public Adopter getAdopterByID(Integer id) {
		if(id == null) return null;
		AccountUser a = userRepo.findAccountUserByuserID(id);
		if(!(a instanceof Adopter)) return null;
		return (Adopter) a;
	}
	
	@Transactional
	public List<AccountUser> getAllAdopters() {
		ArrayList<AccountUser> adopters = new ArrayList<>();
		Iterable<AccountUser> users = userRepo.findAll();
		for(AccountUser user : users) {
			if(user instanceof Adopter) adopters.add(user);
		}
		
		return adopters;
	}
	
	
	@Transactional
	public Owner updateOwner(Owner a) {
		if(a == null || this.getOwnerByID(a.getUserID()) == null) {
			throw new IllegalArgumentException("Cannot update owner that is not in the database");
		}
		userRepo.save(a);
		return a;
	}
	
	@Transactional
	public Owner createOwner(String name, String email, String phone, 
							 String password, String description, Address address,
							 Integer ID, Pet pet) {
		validateAccountUser(name, email, phone, password, description, ID);
		
		int space = name.indexOf(' ');
		
		Owner o = new Owner();
		o.setFirstName(name.substring(0, space));
		o.setLastName(name.substring(space+1));
		
		if(address != null) {
			HashSet<Address> addresses = new HashSet<>();
			addresses.add(address);
			o.setAddress(addresses);
		}
		
		o.setEmailAddress(email);
		o.setPhoneNumer(phone);
		o.setPassword(password);
		o.setDescription(description);
		
		o.setUserID(ID);
		if(pet != null) {
			HashSet<Pet> pets = new HashSet<>();
			pets.add(pet);
			o.setPet(pets);
		}

		userRepo.save(o);
		
		return o;
	}
	
	
	@Transactional
	public Owner createOwner(String name, String email, String password, Integer ID) {
		validateAccountUser(name, email, null, password, null, ID);
		
		int space = name.indexOf(' ');
		
		Owner o = new Owner();
		o.setFirstName(name.substring(0, space));
		o.setLastName(name.substring(space+1));
		
		o.setUserID(ID);
		o.setEmailAddress(email);

		userRepo.save(o);
		
		return o;
	}
	
	@Transactional
	public Owner createOwner(String name, String email, String password, Integer ID, Pet pet) {
		Owner o = createOwner(name, email, password, ID);
		validateAccountUser(name, email, null, password, null, ID);
		
		HashSet<Pet> pets = new HashSet<>();
		pets.add(pet);
		o.setPet(pets);
		userRepo.save(o);
		
		return o;
	}
	
	@Transactional
	public Owner getOwnerByID(Integer id) {
		if(id == null) return null;
		AccountUser o = userRepo.findAccountUserByuserID(id);
		if(!(o instanceof Owner)) return null;
		return (Owner)o;
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
		if(a == null || this.getManagerByID(a.getUserID()) == null) {
			throw new IllegalArgumentException("Cannot update manager that is not in the database");
		}
		userRepo.save(a);
		return a;
	}
	
	@Transactional
	public Manager createManager(String name, String email, String phone, 
							     String password, String description, Address address,
							     Integer ID, Shelter shelter) {
		validateAccountUser(name, email, phone, password, description, ID);
		
		int space = name.indexOf(' ');
		
		Manager m = new Manager();
		m.setFirstName(name.substring(0, space));
		m.setLastName(name.substring(space+1));
		
		if(address != null) {
			HashSet<Address> addresses = new HashSet<>();
			addresses.add(address);
			m.setAddress(addresses);
		}
		
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
	public Manager createManager(String name, String email, String password, Integer ID) {
		validateAccountUser(name, email, null, password, null, ID);
		
		int space = name.indexOf(' ');
		
		Manager m = new Manager();
		m.setFirstName(name.substring(0, space));
		m.setLastName(name.substring(space+1));
		
		m.setPassword(password);
		
		m.setUserID(ID);
		
		userRepo.save(m);
		return m;
	}
	
	@Transactional
	public Manager getManagerByID(Integer id) {
		if(id == null) return null;
		AccountUser a = userRepo.findAccountUserByuserID(id);
		if(! (a instanceof Manager)) return null;
		return (Manager) a;
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
	
	
	private void validateAccountUser(String name, String email, String phone, 
			 						 String password, String description, Integer ID) {
		if(!validEmail(email)) {
			throw new IllegalArgumentException("Invalid Email");
		}
		if(!validName(name)) {
			throw new IllegalArgumentException("Invalid Name");
		}
		if(!validID(ID)) {
			throw new IllegalArgumentException("Invalid User ID (already taken)");
		}
		if(!validPhoneNumber(phone)) {
			throw new IllegalArgumentException("Invalid Phone Number");
		}
		if(!validDescription(description)) {
			throw new IllegalArgumentException("Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
		if(password == null || password.length() < 4 || password.length() > 20) {
			throw new IllegalArgumentException("Invalid Password - must be between 4 and 20 characters");
		}
		
	}
	
	
	
	
	

	/**
	 * Private method to ensure a name string fits the proper format
	 * @param name - name to analyze
	 * @return whether it can be used as a name
	 */
	private boolean validName(String name) {
		int strLen = name.length();
		if(name == null || name.equals("") || strLen < 4 || strLen > 60) return false;
		//Check for only letters
		boolean spaceFound = false;
		for(Character c : name.toCharArray()) {
			//If not a letter (or -) and not the first space, return false
			if(Character.isLetter(c)) continue;
			if(c == '-') continue;
			if(c==' ') {
				if(!spaceFound) {
					spaceFound = true;
					continue;
				}
			}
			
			return false;
		}
		if(!spaceFound) return false;
		int spacePlace = name.indexOf(' ');
		if(spacePlace == 0 || spacePlace == strLen - 1) return false;
		if(name.charAt(spacePlace+1) == '-' || name.charAt(spacePlace-1) == '-') return false;
		//If every character complies
		return true;
	}
	
	/**
	 * Private method to ensure an ID is not be used twice
	 * @param ID - id to check
	 * @return whether it is free
	 */
	private boolean validID(Integer ID) {
		if(ID == null) return false;
		return getAccountUserByID(ID) == null;
	}
	
	/**
	 * Private method to ensure a description is not overboard
	 * @param description - description to check
	 * @return whether it is reasonable
	 */
	private boolean validDescription(String description) {
		//Can have null description
		if(description == null) return true; 
		if(description.length() <= 20 || description.length() >= 1000) return false;
		return true;
	}
	
	/**
	 * Private method to ensure an email string fits the proper format
	 * @param email - email to analyze
	 * @return whether it can be used as an email
	 */
	private boolean validEmail(String email) {
		if(email == null) return false;
		if(email.length() > 20) return false;
		
		boolean atFound = false;
		//Check for one @ at most
		for(Character c : email.toCharArray()) {
			if(c == ' ') return false;
			if(c=='@') {
				if(!atFound) atFound = true;
				else return false;
			}
		}
		//Check there is stuff before @ and between @/.whatever. Accounts for dot not found (-1)
		int atPlace = email.indexOf('@');
		int dotPlace = email.indexOf('.');
		if(atPlace == -1 || dotPlace == -1 || atPlace == 0 || dotPlace == 0) return false;
		//If dot before @ or right after
		if(dotPlace <= atPlace+1) return false;
		
		return true;
		//Ends in .com
		//return email.indexOf(".com") == email.length() - 4;
	}
	
	/**
	 * Private method to ensure a phone number string fits the proper format
	 * @param phoneNumber - number string to analyze
	 * @return whether it can be used as a phone number
	 */
	private boolean validPhoneNumber(String phoneNumber) {
		//Phone Number Format is either: x-xxx-xxx-xxxx or xxx-xxx-XXXX
		//							  or XXXXXXXXXXX or XXXXXXXXXX
		if(phoneNumber == null) return true;
		int strLen = phoneNumber.length();
		//Option 1
		if(strLen == 14) {
			//Check proper dashing
			if(phoneNumber.charAt(1) != '-' || phoneNumber.charAt(5) != '-' || phoneNumber.charAt(9) != '-') return false;
			
			//Check only numbers everywhere else
			if(!phoneNumber.substring(6, 9).matches("\\d+")) return false;
			if(!phoneNumber.substring(10, 14).matches("\\d+")) return false;
			if(!Character.isDigit(phoneNumber.charAt(0))) return false;
			
			return true;
			
		//Option 2
		} else if (strLen == 12) {
			
			//Check proper dashing
			if(phoneNumber.charAt(3) != '-' || phoneNumber.charAt(7) != '-') return false;
			
			//Check only numbers everywhere else
			if(!phoneNumber.substring(4, 7).matches("\\d+")) return false;
			if(!phoneNumber.substring(8).matches("\\d+")) return false;
			
			return true;
			
			
		//Option 3/4
		} else if(strLen == 11 || strLen == 10) {
			if(!phoneNumber.matches("\\d+")) return false;
			return true;
		}
		
		//Not the right size
		return false;
	}
}
