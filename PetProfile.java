import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class PetProfile{
   private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
private String photoURL;

public void setPhotoURL(String value) {
    this.photoURL = value;
}
public String getPhotoURL() {
    return this.photoURL;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
   private Pet pet;
   
   @OneToOne(optional=false)
   public Pet getPet() {
      return this.pet;
   }
   
   public void setPet(Pet pet) {
      this.pet = pet;
   }
   
   private AdoptionPosting adoptionPosting;
   
   @ManyToOne(optional=false)
   public AdoptionPosting getAdoptionPosting() {
      return this.adoptionPosting;
   }
   
   public void setAdoptionPosting(AdoptionPosting adoptionPosting) {
      this.adoptionPosting = adoptionPosting;
   }
   
   }
