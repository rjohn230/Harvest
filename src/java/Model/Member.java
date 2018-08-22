package Model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Member implements Serializable {
    
    @Id
    private long id;
    
    private boolean senderDelete;
    
    
    private boolean receiverDelete;
   
    private long pictureid;
    
    private String pass_salt;
    private String salt;
    
    private String role;
    
    public void setSenderDelete()
    {
        senderDelete=true;
    }
    public void setReceiverDelete()
    {
      receiverDelete=true;   
    }
    
    public boolean getSenderDelete()
    {
        return senderDelete;
    }
    public boolean getReceiverDelete()
    {
        return receiverDelete;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getRole()
    {
        return role;
    }
    public long getPicture()
    {
        return pictureid;
    }
    public void setPictureID() {
        Random brand = new Random();
        pictureid = brand.nextLong();
        if(pictureid<0)
        {
            pictureid=pictureid*-1;
        }
       
    }
    public void setID() {
        Random brand = new Random();
        id = brand.nextLong();
        if(id<0)
        {
            id=id*-1;
        }
       
    }
    public void setRole(String x) {
        role = x;
    }
    
    public void setSalt()
    {
       Random rand = new  SecureRandom();
       
       byte[] saltybytes = new byte[32];
       rand.nextBytes(saltybytes);
       
       salt= Base64.getEncoder().encodeToString(saltybytes);
       
    }
    public String getSalt()
    {
        return salt;
    }
    public void setPasswordSalted(String pass_word)
    {
        setSalt();
        pass_salt=pass_word+salt;
    }
    public String getPassSalt()
    {
        return pass_salt;
    }
   

}
