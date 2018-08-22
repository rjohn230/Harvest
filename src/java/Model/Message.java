package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.TemporalType.DATE;


@Entity
public class Message implements Serializable   {
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date init;
    
   
    @Id
    private long messageid;
    
    private String  message;
    
     private long senderid;
    
    private long receiverid;
    
    private boolean senderDel;
    private boolean receiverDel;
    
    
    public boolean getDelete(long id)
    {
         if (id==senderid)
         {
             return senderDel;
         }
         else return receiverDel;
    }
    
    public void setDelete(long id)
    {
         if (id==senderid)
         {
              senderDel=true;
         }
         else  receiverDel=true;
    }
    
    public String getMessage() 
    {
        return message;
    }
  

    public void setMessage(String message) {
        this.message = message;
    }
    public long getSenderId()
    {
        return senderid;
        
    }
    public long getReceiverId()
    {
        return receiverid;
    }
    
    public void setSender(long id)
    {
        senderid=id;
    }
    public void setReciever(long id)
    {
        receiverid=id;
    }
    
    public long getMessageId()
    {
        return messageid;
    }
    public void setMessageID()
    {
         Random brand = new Random();
        messageid = brand.nextLong();
        if(messageid<0)
        {
            messageid=messageid*-1;
        }
    }
    
    public void setDate()
    {
       init = new Date();
       
    }
    public Date getDate()
    {
        return init;
    }
}
