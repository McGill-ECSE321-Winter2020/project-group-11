package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class ShelterDto {
	private List<PetDto> pets;
	private ManagerDto manager;
	private Integer id;
	
	
	public ShelterDto(Integer id) {
		this.id = id;
	}
	public ShelterDto(Integer id, ManagerDto manager) {
		this.id = id;
		this.manager = manager;
	}
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
