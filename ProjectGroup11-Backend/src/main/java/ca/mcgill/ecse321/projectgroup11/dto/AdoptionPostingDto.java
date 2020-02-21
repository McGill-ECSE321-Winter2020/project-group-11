package ca.mcgill.ecse321.projectgroup11.dto;

import java.util.List;

public class AdoptionPostingDto {
	private Integer id;
	private List<AdopterDto> adopters;
	private PetDto pet;

	public AdoptionPostingDto(Integer id) {
		this.id = id;
	}
	public AdoptionPostingDto(Integer id, List<AdopterDto> adopters) {
		this.id = id;
		this.adopters = adopters;
	}
	public AdoptionPostingDto(Integer id, PetDto pet) {
		this.id = id;
		this.pet = pet;
	}
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
