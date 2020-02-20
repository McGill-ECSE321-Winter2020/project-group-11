package ca.mcgill.ecse321.projectgroup11.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup11.dto.AccountUserDto;
import ca.mcgill.ecse321.projectgroup11.dto.AdopterDto;
import ca.mcgill.ecse321.projectgroup11.dto.ManagerDto;
import ca.mcgill.ecse321.projectgroup11.dto.OwnerDto;
import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.service.AccountUserService;

@CrossOrigin(origins = "*")
@RestController
public class AccountUserRestController {

	
	@Autowired
	private AccountUserService service;
	
	
	
	@GetMapping(value = { "/users", "/users/" })
	public List<AccountUserDto> getAllUsers() {
		return service.getAllAccountUsers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/adopters", "/adopters/" })
	public List<AdopterDto> getAllAdopters() {
		return service.findAllAdopters().stream().map(p -> convertToAdopterDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/owners", "/owners/" })
	public List<OwnerDto> getAllOwners() {
		return service.findAllOwners().stream().map(p -> convertToOwnerDto(p)).collect(Collectors.toList());
	}
	@GetMapping(value = { "/managers", "/managers/" })
	public List<ManagerDto> getAllManagers() {
		return service.findAllOwners().stream().map(p -> convertToManagerDto(p)).collect(Collectors.toList());
	}

	
	@PostMapping(value = { "/users/{name}/{password}/{ID}", "/users/{name}/{password}/{ID}/" })
	public AccountUserDto createUser(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space = name.indexOf(' ');
		AccountUser user = service.createAdopter(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToDto(user);
	}
	@PostMapping(value = { "/adopters/{name}/{password}/{ID}", "/users/{name}/{password}/{ID}/" })
	public AdopterDto createAdopter(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space = name.indexOf(' ');
		Adopter user = service.createAdopter(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToAdopterDto(user);
	}
	@PostMapping(value = { "/owners/{name}/{password}/{ID}", "/users/{name}/{password}/{ID}/" })
	public OwnerDto createOwner(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space = name.indexOf(' ');
		Owner owner = service.createOwner(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToOwnerDto(owner);
	}
	@PostMapping(value = { "/managers/{name}/{password}/{ID}", "/users/{name}/{password}/{ID}/" })
	public ManagerDto createManager(@PathVariable("name") String name, @PathVariable("password") String pass, 
									   @PathVariable("ID") Integer id) throws IllegalArgumentException {
		int space = name.indexOf(' ');
		Manager manage = service.createManager(name.substring(0, space), name.substring(space+1), pass, id);
		return convertToManagerDto(manage);
	}
	
	
	private AccountUserDto convertToDto(AccountUser accU) {
		if (accU == null) {
			throw new IllegalArgumentException("There is no such AccountUser!");
		}
		AccountUserDto  aDto = new AdopterDto(accU.getUserID(), accU.getFirstName(), accU.getLastName(), accU.getPassword());
		return aDto;
	}

	
	private AdopterDto convertToAdopterDto(AccountUser accU) {
		if (accU == null || !(accU instanceof Adopter)) {
			throw new IllegalArgumentException("There is no such Adopter!");
		}
		Adopter a = (Adopter) accU;
		AdopterDto  aDto = new AdopterDto(a.getUserID(), a.getFirstName(), a.getLastName(), a.getPassword());
		return aDto;
	}

	private OwnerDto convertToOwnerDto(AccountUser accU) {
		if (accU == null|| !(accU instanceof Owner)) {
			throw new IllegalArgumentException("There is no such Owner!");
		}
		Owner o = (Owner) accU;
		OwnerDto oDto = new OwnerDto(o.getUserID(), o.getFirstName(), o.getLastName(), o.getPassword());
		return oDto;
	}
	
	private ManagerDto convertToManagerDto(AccountUser accU) {
		if (accU == null|| !(accU instanceof Manager)) {
			throw new IllegalArgumentException("There is no such Manager!");
		}
		Manager m = (Manager) accU;
		ManagerDto mDto = new ManagerDto(m.getUserID(), m.getFirstName(), m.getLastName(), m.getPassword());
		return mDto;
	}
	
	
}
