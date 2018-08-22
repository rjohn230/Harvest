package Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Professional {
    
    
    private String email;
    

    private String phone;
    
    private String firstName;
    
    private String lastName;
    
    private String profession;
    
    private String education;
    
    private String about;
   
    @Id
  @OneToOne (fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    private Member member;
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    public void setEmail(String x)
    {
        email=x;
    }
    
    public void setAbout(String x)
    {
        about=x;
    }
    public void setEducation(String x)
    {
        education=x;
    }
    public void setFirstName(String x)
    {
        firstName=x;
    }
    public void setLastName(String x)
    {
        lastName=x;
    }
    public void setPhone(String x)
    {
        phone =x;
    }
    public void setProfession(String x)
    {
        profession=x;
    }
    
    public String getAbout()
    {
        return about;
    }
    public String getEducation()
    {
        return education;
    }
    public String getEmail()
    {
        return email;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
   
    public String getProfession()
    {
        return profession;
    }
    public String getPhone()
    {
        return phone;
    }
   

    public Member getMember() {
        return member;
    }

}
