import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class AdoptionPosting{
   private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
   private Set<PetProfile> petProfile;
   
   @OneToMany(mappedBy="adoptionPosting" )
   public Set<PetProfile> getPetProfile() {
      return this.petProfile;
   }
   
   public void setPetProfile(Set<PetProfile> petProfiles) {
      this.petProfile = petProfiles;
   }
   
   private Set<Adopter> adopter;
   
   @OneToMany(mappedBy="adoptionPosting" )
   public Set<Adopter> getAdopter() {
      return this.adopter;
   }
   
   public void setAdopter(Set<Adopter> adopters) {
      this.adopter = adopters;
   }
   
   }
