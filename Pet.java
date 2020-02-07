import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pet{
   private String type;

public void setType(String value) {
    this.type = value;
}
public String getType() {
    return this.type;
}
   private Set<Owner> owner;
   
   @OneToMany(mappedBy="pet" )
   public Set<Owner> getOwner() {
      return this.owner;
   }
   
   public void setOwner(Set<Owner> owners) {
      this.owner = owners;
   }
   
   private PetProfile petProfile;
   
   @OneToOne(mappedBy="pet" , optional=false)
   public PetProfile getPetProfile() {
      return this.petProfile;
   }
   
   public void setPetProfile(PetProfile petProfile) {
      this.petProfile = petProfile;
   }
   
   private Set<Shelter> shelter;
   
   @OneToMany(mappedBy="pet" )
   public Set<Shelter> getShelter() {
      return this.shelter;
   }
   
   public void setShelter(Set<Shelter> shelters) {
      this.shelter = shelters;
   }
   
   }
