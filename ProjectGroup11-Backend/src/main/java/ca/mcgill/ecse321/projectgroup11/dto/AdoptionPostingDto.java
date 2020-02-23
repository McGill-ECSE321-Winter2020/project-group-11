package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class AdoptionPostingDto {
	private Integer id;
	private List<AdopterDto> adopters;
	private PetDto pet;
	/* We wrote here all the possible ways to create an AdopttionPostingDto object using many different constructors with different input (with respect to the constraints we decided (for instance : every object should have an id , so the id param will be there for each constructor)

	/**
	 * 
	 * @param id
	 */
	public AdoptionPostingDto(Integer id) {
		this.id = id;
	}
	/**
	 * 
	 * @param id
	 * @param adopters
	 */
	public AdoptionPostingDto(Integer id, List<AdopterDto> adopters) {
		this.id = id;
		this.adopters = adopters;
	}
	/**
	 * 
	 * @param id
	 * @param pet
	 */
	public AdoptionPostingDto(Integer id, PetDto pet) {
		this.id = id;
		this.pet = pet;
	}
	/**
	 * 
	 * @param id
	 * @param pet
	 * @param adopters
	 */
	public AdoptionPostingDto(Integer id, PetDto pet, List<AdopterDto> adopters) {
		this.id = id;
		this.pet = pet;
		this.adopters = adopters;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	public List<AdopterDto> getAdopters() {
		return this.adopters;
	}
	public PetDto getPet() {
		return this.pet;
	}
}
