package Model;

import java.util.Date;

public class Directed {
    
    private String message;
    
    private String  name;
    
    private String profile;
    
    private Date date;
    
    
    private long id;
    
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date dd)
    {
        date=dd;
    }
    
    public long getId()
    {
        return id;
    }
    
    public void setId(long x)
    {
        id=x;
    }
 
    
    
    public String getMessage()
    {
        return message;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getProfile()
    {
        return profile;
    }
    
    public void setMessage(String x)
    {
        message=x;
    }
    public void setName(String x)
    {
        name=x;
    }
    public void setProfile(String x)
    {
        profile=x;
    }

}
