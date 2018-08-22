package Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@CascadeOnDelete
public class Ambassador implements Serializable {
    
    
    
    
    private String email;
    
     private String name;
     
      private String school;
      
       
        private String phone;
        
         private String position;
         
         @Id
         @OneToOne (fetch= FetchType.EAGER, cascade=CascadeType.ALL)
         private Member member;

    

    
         
    public void setEmail(String x)
    {
        email=x;
    }
    public void setName(String x)
    {
        name=x;
    }
    public void setPhone(String x)
    {
        phone=x;
    }
    public void setPosition(String x)
    {
        position=x;
    }
    public void setSchool(String x)
    {
        school=x;
    }
    
    public String getEmail()
    {
        return email;
    }
    public String getName()
    {
        return name;
    }
    public String getPhone()
    {
        return phone;
    }
    
    public String getPosition()
    {
        return position;
    }
    public String getSchool()
    {
        return school;
    }
 

    public void setMember(Member member) {
        this.member = member;
    }
    public Member getMember(){
        return member;
    }


}
