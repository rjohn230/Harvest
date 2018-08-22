package Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Subject {
    
    private String cs;
    
    private String med;
    
    private String egr;
    
    private String sci;
    
    private String law;
    
    private String bus;
    
    
    
    
    
    
    @Id
    @OneToOne
    private Member member;
    
    public void setMember(long id)
    {
        member = MemberDB.findMember(id);
    }
    
    public void setCS(String x)
    {
        cs=x;
    }
    public void setMed(String x)
    {
        med=x;
    }
    public void setSci(String x)
    {
        sci=x;
    }
    public void setEgr(String x)
    {
        egr=x;
    }
    
     public void setLaw(String x)
    {
        law=x;
    }
    
    public void setBus(String x)
    {
        bus=x;
    }
    public String getCS()
    {
        return cs;
    }
    public String getEgr()
    {
        return egr;
    }
    public String getMed()
    {
        return med;
    }
    public String getBus()
    {
        return bus;
    }
    public String getSci()
    {
        return sci;
    }
    public String getLaw()
    {
        return law;
    }
    public long getID()
    {
        return member.getId();
    }
    
   
    

}
