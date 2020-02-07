import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Address{
   private String street;

public void setStreet(String value) {
    this.street = value;
}
public String getStreet() {
    return this.street;
}
private int streetNumber;

public void setStreetNumber(int value) {
    this.streetNumber = value;
}
public int getStreetNumber() {
    return this.streetNumber;
}
private String city;

public void setCity(String value) {
    this.city = value;
}
public String getCity() {
    return this.city;
}
private String province;

public void setProvince(String value) {
    this.province = value;
}
public String getProvince() {
    return this.province;
}
private String postalCode;

public void setPostalCode(String value) {
    this.postalCode = value;
}
public String getPostalCode() {
    return this.postalCode;
}
   private User user;
   
   @ManyToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   }
