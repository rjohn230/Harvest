package Model;


import DataBase.DButil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class MessageDB {
    
    public static List<Undirected> getUndirected(long whoid)
    {
        List<Message> result = new ArrayList<Message>();
        
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   m FROM Message m " +
                "WHERE m.senderid = :whoid OR m.receiverid = :whoid "
                ;
      
        TypedQuery<Message> q = em.createQuery(qString, Message.class);
        
        q.setParameter("whoid",whoid);
        
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            
            em.close();
            return null;
        } 
       
       
       qString = " SELECT m FROM Member m "
               + " WHERE m.id = :whoid";
       Member mem = new Member();
       
        TypedQuery<Member> qtwo = em.createQuery(qString, Member.class);
        
        qtwo.setParameter("whoid",whoid);
        
        try {
             mem = qtwo.getSingleResult();
            
        } catch (NoResultException e) {
            em.close();
            return null;
        } 
        
        if(mem.getRole().equalsIgnoreCase("ambassador"))
        {
            List<Professional> pros = new ArrayList<>();
            
             qString ="SELECT p FROM Member m"
                            + ", Professional p WHERE m = p.member AND m.id = :id";
             TypedQuery<Professional> qthee = em.createQuery(qString, Professional.class);
            
            for(Message message: result)
            {
                if( message.getDelete(whoid))
                {
                    break;
                }
                
                else if(message.getSenderId()== whoid )
                {
                   qthee.setParameter("id", message.getReceiverId());
                   
                }
                else if(message.getReceiverId()== whoid)
                {
                    qthee.setParameter("id", message.getSenderId());
                }
                
                try {
                     if(!pros.contains(qthee.getSingleResult()))
                     {
                         pros.add(qthee.getSingleResult());
                     }
            
               } 
                catch (NoResultException e) {
                   break;
                    
                                            }
                
                                            
               
                
            }
            
            
            
            List<Undirected> undirected = new ArrayList<>();
            
            for(Professional p : pros)
            {
                Undirected sam = new Undirected(p.getFirstName()+" "+p.getLastName(),
                        p.getMember().getPicture(),
                        p.getMember().getId());
                
                undirected.add(sam);
            }
            
                       

                        

            
            return undirected;
        }
        
        
        
        
        else if(mem.getRole().equalsIgnoreCase("professional")){
            
            List<Ambassador> ambass = new ArrayList<Ambassador>();
             qString ="SELECT a FROM Member m"
                            + ", Ambassador a WHERE m = a.member AND m.id = :id";
             TypedQuery<Ambassador> qthee = em.createQuery(qString, Ambassador.class);
            
            for(Message message: result)
            {
                if(message.getSenderId()== whoid)
                {
                   qthee.setParameter("id", message.getReceiverId());
                   
                }
                else
                {
                    qthee.setParameter("id", message.getSenderId());
                }
                try {
                     if(!ambass.contains(qthee.getSingleResult()))
                     {
                         ambass.add(qthee.getSingleResult());
                     }
            
               } 
                catch (NoResultException e) {
                    break;
                                            } 
            }
            
            
            
            List<Undirected> undirected = new ArrayList<>();
            
            for(Ambassador p : ambass)
            {
                Undirected sam = new Undirected(p.getName(),
                        p.getMember().getPicture(),
                        p.getMember().getId());
                
                undirected.add(sam);
            }
            
            
            
            return undirected;
            
        }
       
       
       return null;
       
        
        
    }
    
    public static List<Directed> getDirected(long id, long otherid)
    {
        List<Message> result = new ArrayList<Message>();
 
        
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT m FROM Message m " +
                "WHERE (m.senderid = :whoid AND m.receiverid = :other) OR (m.receiverid = :whoid "
                + " AND m.senderid = :other )"
                + " ORDER BY m.init "
                ;
      
        TypedQuery<Message> q = em.createQuery(qString, Message.class);
        
        q.setParameter("whoid",id);
        q.setParameter("other",otherid);
        
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            
            em.close();
            return null;
        }  
       List<Directed> dir = new ArrayList<>();
       
       for(Message message : result )
       {
           Directed director = new Directed();
           
           director.setMessage(message.getMessage());
           
           director.setId(message.getSenderId());
           director.setDate(message.getDate());
           
           dir.add(director);
       }
        
        
        return dir;
    }
    
    
    public static void insertMessage (Message message)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(message);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    
    
}
