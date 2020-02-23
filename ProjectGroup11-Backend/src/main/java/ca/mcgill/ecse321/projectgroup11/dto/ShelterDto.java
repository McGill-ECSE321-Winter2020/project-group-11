package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class ShelterDto {
	private List<PetDto> pets;
	private ManagerDto manager;
	private Integer id;
	/* We wrote here all the possible ways to create an Shelter object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)

	
	/**
	 * 
	 * @param id
	 */
	public ShelterDto(Integer id) {
		this.id = id;
	}
	/**
	 * 
	 * @param id
	 * @param manager
	 */
	public ShelterDto(Integer id, ManagerDto manager) {
		this.id = id;
		this.manager = manager;
	}
	/**
	 * 
	 * @param id
	 * @param pets
	 */
	public ShelterDto(Integer id, List<PetDto> pets) {
		this.id = id;
		this.pets = pets;
	}
	/**
	 * 
	 * @param id
	 * @param manager
	 * @param pets
	 */
	public ShelterDto(Integer id, ManagerDto manager, List<PetDto> pets) {
		this.id = id;
		this.manager = manager;
		this.pets = pets;
	}
	
	public List<PetDto> getPet() {
		return this.pets;
	}
	public ManagerDto getManager() {
		return this.manager;
	}
	public Integer getId() {
		return this.id;
	}
}
