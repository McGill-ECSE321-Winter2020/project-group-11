import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Adopter extends User{
   private AdoptionPosting adoptionPosting;
   
   @ManyToOne(optional=false)
   public AdoptionPosting getAdoptionPosting() {
      return this.adoptionPosting;
   }
   
   public void setAdoptionPosting(AdoptionPosting adoptionPosting) {
      this.adoptionPosting = adoptionPosting;
   }
   
   }
