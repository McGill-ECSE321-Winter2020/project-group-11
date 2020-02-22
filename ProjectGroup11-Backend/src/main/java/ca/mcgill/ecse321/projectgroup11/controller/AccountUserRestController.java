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
	
	@PostMapping(value = { "/adopters/{name}/{email}/{password}/{ID}", "/adopters/{name}/{email}/{password}/{ID}/" })
	public AdopterDto createAdopter(@PathVariable("name") String name, @PathVariable("password") String pass, 
									@PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		Adopter a = service.createAdopter(name, email, pass, id);
		return convertToAdopterDto(a);
	}
	@PostMapping(value = { "/adopters/{name}/{email}/{password}/{ID}/{phone}", "/adopters/{name}/{email}/{password}/{ID}/{phone}/" })
	public AdopterDto createAdopter(@PathVariable("name") String name, @PathVariable("password") String pass, 
									@PathVariable("ID") Integer id, @PathVariable("email") String email,
									@PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		Adopter a = service.createAdopter(name, email, phoneNumber, pass, null, null, id);
		return convertToAdopterDto(a);
	}

	@PostMapping(value = { "/owners/{name}/{email}/{password}/{ID}", "/owners/{name}/{email}/{password}/{ID}/" })
	public OwnerDto createOwner(@PathVariable("name") String name, @PathVariable("password") String pass, 
							    @PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		Owner owner = service.createOwner(name, email, pass, id);
		return convertToOwnerDto(owner);
	}
	@PostMapping(value = { "/owners/{name}/{email}/{password}/{ID}/{phone}", "/owners/{name}/{email}/{password}/{ID}/{phone}/" })
	public OwnerDto createOwner(@PathVariable("name") String name, @PathVariable("password") String pass, 
							    @PathVariable("ID") Integer id, @PathVariable("email") String email,
							    @PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		Owner owner = service.createOwner(name, email, phoneNumber, pass, null, null, id, null);
		return convertToOwnerDto(owner);
	}
	

	@PostMapping(value = { "/managers/{name}/{email}/{password}/{ID}", "/managers/{name}/{email}/{password}/{ID}/" })
	public ManagerDto createManager(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id, @PathVariable("email") String email) throws IllegalArgumentException {
		Manager manage = service.createManager(name, email, pass, id);
		return convertToManagerDto(manage);
	}
	@PostMapping(value = { "/managers/{name}/{email}/{password}/{ID}/{phone}", "/managers/{name}/{email}/{password}/{ID}/{phone}/" })
	public ManagerDto createManager(@PathVariable("name") String name, @PathVariable("password") String pass, 
									@PathVariable("ID") Integer id, @PathVariable("email") String email,
									@PathVariable("phone") String phoneNumber) throws IllegalArgumentException {
		Manager manage = service.createManager(name, email, phoneNumber, pass, null, null, id, null);
		return convertToManagerDto(manage);
	}
	
	@PostMapping(value = { "/address/{ID}/{streetNum}/{street}/{city}/{province}/{postal}", "/address/{ID}/{streetNum}/{street}/{city}/{province}/{postal}/" })
	public AddressDto createAddress(@PathVariable("ID") Integer id, @PathVariable("streetNum") Integer streetNum, 
									@PathVariable("street") String street, @PathVariable("city") String city,
									@PathVariable("province") String province, @PathVariable("postal") String postal) throws IllegalArgumentException {
		
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
		} else {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress());
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
			List<PetDto> petsDto = pets.stream().map(p -> new PetDto(p.getId())).collect(Collectors.toList());
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
		} else {
			oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getEmailAddress());
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
		} else {
			mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getEmailAddress());
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
		} else {
			aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getEmailAddress());
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
	
	
	
}
