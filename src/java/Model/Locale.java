package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locale implements Serializable {
    
    private String state;
    private String city;
    private String country;
    
    @Id
    @OneToOne
    private Member member;
    
    public void setMember(long id)
    {
        member = MemberDB.findMember(id);
    }
    
    public void setCity(String x)
    {
        city=x;
    }
    
    public void setState(String x)
    {
        state=x;
    }
    public void setCountry(String x)
    {
        country=x;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }
    public String getCountry()
    {
        return country;
    }
    public long getID()
    {
        return member.getId();
    }

}
