import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Shelter{
   private Pet pet;
   
   @ManyToOne(optional=false)
   public Pet getPet() {
      return this.pet;
   }
   
   public void setPet(Pet pet) {
      this.pet = pet;
   }
   
   }
