package ca.mcgill.ecse321.projectgroup11.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup11.dto.AccountUserDto;
import ca.mcgill.ecse321.projectgroup11.dto.AddressDto;
import ca.mcgill.ecse321.projectgroup11.dto.AdopterDto;
import ca.mcgill.ecse321.projectgroup11.dto.AdoptionPostingDto;
import ca.mcgill.ecse321.projectgroup11.dto.ManagerDto;
import ca.mcgill.ecse321.projectgroup11.dto.OwnerDto;
import ca.mcgill.ecse321.projectgroup11.dto.PetDto;
import ca.mcgill.ecse321.projectgroup11.dto.ShelterDto;
import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Address;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.service.AccountUserService;
import ca.mcgill.ecse321.projectgroup11.service.AddressService;

@CrossOrigin(origins = "*")
@RestController
public class AccountUserRestController {

	
	@Autowired
	private AccountUserService service;
	
	@Autowired
	private AddressService addService;
	
	
	
	@GetMapping(value = { "/users", "/users/" })
	public List<AccountUserDto> getAllUsers() {
		return service.getAllAccountUsers().stream().map(p -> convertToAccountUserDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/adopters", "/adopters/" })
	public List<AdopterDto> getAllAdopters() {
		return service.getAllAdopters().stream().map(p -> convertToAdopterDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/owners", "/owners/" })
	public List<OwnerDto> getAllOwners() {
		return service.getAllOwners().stream().map(p -> convertToOwnerDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/managers", "/managers/" })
	public List<ManagerDto> getAllManagers() {
		return service.getAllManagers().stream().map(p -> convertToManagerDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/addresses", "/addresses/" })
	public List<AddressDto> getAllAddresses() {
		return addService.getAllAddresses().stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
	}

	//3 general Account User cases - each more specific (note, these shouldn't be used in the final application)
	@PostMapping(value = { "/users/{name}/{password}/{ID}", "/users/{name}/{password}/{ID}/" })
	public AccountUserDto createUser(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space;
		if(validName(name)) {
			space = name.indexOf(' ');
		} else return null;
		if(!validID(id)) return null;
		if(pass.length() > 20) return null;
		
		AccountUser user = service.createAdopter(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToAccountUserDto(user);
	}
	@PostMapping(value = { "/users/{name}/{password}/{ID}/{email}", "/users/{name}/{password}/{ID}/{email}" })
	public AccountUserDto createUser(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		if(!validEmail(email)) return null;
		
		if(createUser(name, pass, id) == null) return null;
		AccountUser user = service.getAccountUserByID(id);
		user.setEmailAddress(email);
		service.updateAccountUser(user);
		return convertToAccountUserDto(user);
	}
	@PostMapping(value = { "/users/{name}/{password}/{ID}/{email}/{phone}", "/users/{name}/{password}/{ID}/{email}/{phone}/" })
	public AccountUserDto createUser(@PathVariable("name") String name, @PathVariable("password") String pass, 
									 @PathVariable("ID") Integer id, @PathVariable("email") String email,
									 @PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		if(!validPhoneNumber(phoneNumber)) return null;
		
		if(createUser(name, pass, id, email) == null) return null;
		AccountUser user = service.getAccountUserByID(id);
		user.setPhoneNumer(phoneNumber);
		service.updateAccountUser(user);
		return convertToAccountUserDto(user);
	}
	
	//3 Adopter cases - each more specific
	@PostMapping(value = { "/adopters/{name}/{password}/{ID}", "/adopters/{name}/{password}/{ID}/" })
	public AdopterDto createAdopter(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space;
		if(validName(name)) {
			space = name.indexOf(' ');
		} else return null;
		if(!validID(id)) return null;
		if(pass.length() > 20) return null;
		
		Adopter user = service.createAdopter(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToAdopterDto(user);
	}
	
	@PostMapping(value = { "/adopters/{name}/{password}/{ID}/{email}", "/adopters/{name}/{password}/{ID}/{email}/" })
	public AdopterDto createAdopter(@PathVariable("name") String name, @PathVariable("password") String pass, 
									@PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		if(!validEmail(email)) return null;
		
		if(createAdopter(name, pass, id) == null) return null;
		Adopter a = service.getAdopterByID(id);
		a.setEmailAddress(email);
		service.updateAdopter(a);
		
		return convertToAdopterDto(a);
	}
	@PostMapping(value = { "/adopters/{name}/{password}/{ID}/{email}/{phone}", "/adopters/{name}/{password}/{ID}/{email}/{phone}/" })
	public AdopterDto createAdopter(@PathVariable("name") String name, @PathVariable("password") String pass, 
									@PathVariable("ID") Integer id, @PathVariable("email") String email,
									@PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		if(!validPhoneNumber(phoneNumber)) return null;
		
		if(createAdopter(name, pass, id, email) == null) return null;
		Adopter a = service.getAdopterByID(id);
		a.setPhoneNumer(phoneNumber);
		service.updateAdopter(a);
		
		return convertToAdopterDto(a);
	}
	
	//Three Owner cases - increasingly descriptive
	@PostMapping(value = { "/owners/{name}/{password}/{ID}", "/owners/{name}/{password}/{ID}/" })
	public OwnerDto createOwner(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space;
		if(validName(name)) {
			space = name.indexOf(' ');
		} else return null;
		if(!validID(id)) return null;
		
		Owner owner = service.createOwner(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToOwnerDto(owner);
	}
	@PostMapping(value = { "/owners/{name}/{password}/{ID}/{email}", "/owners/{name}/{password}/{ID}/{email}/" })
	public OwnerDto createOwner(@PathVariable("name") String name, @PathVariable("password") String pass, 
							    @PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		if(!validEmail(email)) return null;
		
		if(createOwner(name, pass, id) == null) return null;
		Owner owner = service.getOwnerByID(id);
		owner.setEmailAddress(email);
		service.updateOwner(owner);
		return convertToOwnerDto(owner);
	}
	@PostMapping(value = { "/owners/{name}/{password}/{ID}/{email}/{phone}", "/owners/{name}/{password}/{ID}/{email}/{phone}/" })
	public OwnerDto createOwner(@PathVariable("name") String name, @PathVariable("password") String pass, 
							    @PathVariable("ID") Integer id, @PathVariable("email") String email,
							    @PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		if(!validPhoneNumber(phoneNumber)) return null;
		
		if(createOwner(name, pass, id, email) == null) return null;
		Owner owner = service.getOwnerByID(id);
		owner.setPhoneNumer(phoneNumber);
		service.updateOwner(owner);
		return convertToOwnerDto(owner);
	}
	
	
	//Three Manager cases - increasingly descriptive
	@PostMapping(value = { "/managers/{name}/{password}/{ID}", "/managers/{name}/{password}/{ID}/" })
	public ManagerDto createManager(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space;
		if(validName(name)) {
			space = name.indexOf(' ');
		} else return null;
		if(!validID(id)) return null;
		
		Manager manage = service.createManager(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToManagerDto(manage);
	}
	@PostMapping(value = { "/managers/{name}/{password}/{ID}/{email}", "/managers/{name}/{password}/{ID}/{email}/" })
	public ManagerDto createManager(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		if(!validEmail(email)) return null;
		
		if(createManager(name, pass, id) == null) return null;
		Manager manage = service.getManagerByID(id);
		manage.setEmailAddress(email);
		service.updateManager(manage);
		return convertToManagerDto(manage);
	}
	@PostMapping(value = { "/managers/{name}/{password}/{ID}/{email}/{phone}", "/managers/{name}/{password}/{ID}/{email}/{phone}" })
	public ManagerDto createManager(@PathVariable("name") String name, @PathVariable("password") String pass, 
									@PathVariable("ID") Integer id, @PathVariable("email") String email,
									@PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		if(!validPhoneNumber(phoneNumber)) return null;
		
		if(createManager(name, pass, id, email) == null) return null;
		Manager manage = service.getManagerByID(id);
		manage.setPhoneNumer(phoneNumber);
		service.updateManager(manage);
		return convertToManagerDto(manage);
	}
	
	@PostMapping(value = { "/address/{ID}/{streetNum}/{street}/{city}/{province}/{postal}", "/address/{ID}/{streetNum}/{street}/{city}/{province}/{postal}/" })
	public AddressDto createAddress(@PathVariable("ID") Integer id, @PathVariable("streetNum") Integer streetNum, 
									@PathVariable("street") String street, @PathVariable("city") String city,
									@PathVariable("province") String province, @PathVariable("postal") String postal) throws IllegalArgumentException {
		
		if(!street.matches("[a-zA-Z ]+")) return null;
		int spaceIndex = street.indexOf(' ');
		if(spaceIndex == -1 || spaceIndex == 0 || 
		  spaceIndex == street.length() - 1 || spaceIndex != street.lastIndexOf(' ')) return null;
		
		if(!city.matches("[a-zA-Z]+")) return null;
		if(!province.matches("[a-zA-Z]+")) return null;
		Address a = addService.createAddress(id, streetNum, street, city, province, postal);
		
		return convertToAddressDto(a);
	}
	
	@PostMapping(value = { "/userAddress/{UserID}/{AddressID}", "/userAdress/{UserID}/{AddressID}/" })
	public AccountUserDto linkUserAndAddress(@PathVariable("UserID") Integer UserID, @PathVariable("AddressID") Integer AddressID) throws IllegalArgumentException {
		
		AccountUser user = service.getAccountUserByID(UserID);
		if(user.getAddress() == null) {
			user.setAddress(new HashSet<Address>());
		}
		user.getAddress().add(addService.getAddressById(AddressID));
		service.updateAccountUser(user);
		return convertToAccountUserDto(user);
	}
	
	/**
	 * Create an Adopter Data Transfer Object based on an Account User object
	 * @param accU - AccountUser to summarize with DTO
	 * @return AdopterDto
	 */
	private AdopterDto convertToAdopterDto(AccountUser accU) {
		if (accU == null || !(accU instanceof Adopter)) {
			throw new IllegalArgumentException("There is no such Adopter!");
		}
		Adopter a = (Adopter) accU;
		AdopterDto aDto;
		if (a.getAdoptionPosting() != null) {
			Set<Address> add = a.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer(), a.getDescription(), addDto,
					new AdoptionPostingDto(a.getAdoptionPosting().getId()));
		} else if (a.getAddress() != null) {
			Set<Address> add = a.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer(), a.getDescription(), addDto);
		} else if (a.getDescription() != null) {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer(), a.getDescription());
		} else if (a.getPhoneNumer() != null) {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer());
		} else if (a.getEmailAddress() != null) {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress());
		} else {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName());
		}

		return aDto;
	}

	/**
	 * Create an Owner Data Transfer Object based on an Account User object
	 * @param accU - AccountUser to summarize with DTO
	 * @return OwnerDto
	 */
	private OwnerDto convertToOwnerDto(AccountUser accU) {
		if (accU == null|| !(accU instanceof Owner)) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		
		Owner o = (Owner) accU;
		OwnerDto oDto;
		if(o.getPet() != null) {
			Set<Address> add = o.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			Set<Pet> pets = o.getPet();
			List<PetDto> petsDto = pets.stream().map(p -> new PetDto(p.getId(), p.getType())).collect(Collectors.toList());
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getEmailAddress(),
								  o.getPhoneNumer(), o.getDescription(), addDto, petsDto);
		} else if(o.getAddress() != null) {
			Set<Address> add = o.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getEmailAddress(),
								  o.getPhoneNumer(), o.getDescription(), addDto);
		} else if(o.getDescription() != null) {
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getEmailAddress(),
								  o.getPhoneNumer(), o.getDescription());
		} else if(o.getPhoneNumer() != null) {
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getEmailAddress(),
					  o.getPhoneNumer());
		} else if(o.getEmailAddress() != null) {
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getEmailAddress());
		} else {
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName());
		}
	
		return oDto;
	}
	
	/**
	 * Create a Manager Data Transfer Object based on an Account User object
	 * @param accU - AccountUser to summarize with DTO
	 * @return ManagerDto
	 */
	private ManagerDto convertToManagerDto(AccountUser accU) {
		if (accU == null|| !(accU instanceof Manager)) {
			throw new IllegalArgumentException("There is no such Manager!");
		}
		
		Manager m = (Manager) accU;
		ManagerDto mDto;
		if(m.getShelter() != null) {
			Set<Address> add = m.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getEmailAddress(),
								  m.getPhoneNumer(), m.getDescription(), addDto, new ShelterDto(m.getShelter().getId()));
		} else if(m.getAddress() != null) {
			Set<Address> add = m.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getEmailAddress(),
								  m.getPhoneNumer(), m.getDescription(), addDto);
		} else if(m.getDescription() != null) {
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getEmailAddress(),
								  m.getPhoneNumer(), m.getDescription());
		} else if(m.getPhoneNumer() != null) {
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getEmailAddress(),
					  			  m.getPhoneNumer());
		} else if(m.getEmailAddress() != null) {
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getEmailAddress());
		} else {
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName());
		}
		return mDto;
	}
	
	/**
	 * Create an Account User Data Transfer Object based on an Account User object
	 * @param accU - AccountUser to summarize with DTO
	 * @return AccountUserDto
	 */
	private AccountUserDto convertToAccountUserDto(AccountUser a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such User!");
		}
		
		AccountUserDto aDto;
		if (a.getAddress() != null) {
			Set<Address> add = a.getAddress();
			List<AddressDto> addDto = add.stream().map(p -> convertToAddressDto(p)).collect(Collectors.toList());
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer(), a.getDescription(), addDto);
		} else if (a.getDescription() != null) {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer(), a.getDescription());
		} else if (a.getPhoneNumer() != null) {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress(),
					a.getPhoneNumer());
		} else if (a.getEmailAddress() != null) {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress());
		} else {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName());
		}

		return aDto;
	}
	
	/**
	 * Create an Address Data Transfer Object based on an Adress object
	 * @param a - Address to summarize with DTO
	 * @return AddressDTO
	 */
	private AddressDto convertToAddressDto(Address a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Address!");
		}
		
		AddressDto add = new AddressDto(a.getId(), a.getStreetNumber(), a.getStreet(), a.getCity(), a.getProvince(), a.getPostalCode());
		return add;
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
		return service.getAccountUserByID(ID) == null;
	}
	
	/**
	 * Private method to ensure an email string fits the proper format
	 * @param email - email to analyze
	 * @return whether it can be used as an email
	 */
	private boolean validEmail(String email) {
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
		//Phone Number Format is either: X-XXX-XXX-XXXX or XXX-XXX-XXXX
		//							  or XXXXXXXXXXX or XXXXXXXXXX
		
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
			if(!phoneNumber.substring(8, 12).matches("\\d+")) return false;
			
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
