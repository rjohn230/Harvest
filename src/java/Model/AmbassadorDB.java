package Model;

import DataBase.DButil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AmbassadorDB {
    
    public static void insertAmbassador (Ambassador ambass)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(ambass);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Ambassador findAmbassador(String email)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   a FROM Ambassador a " +
                " WHERE a.email = :email"
                ;
      
        TypedQuery<Ambassador> q = em.createQuery(qString, Ambassador.class);
        
        q.setParameter("email",email);
        Ambassador JohnDoe = new Ambassador();       
        try {
            JohnDoe = q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
            
        } finally {
            em.close();
        }
        
        return JohnDoe;
        
    }
    
    public static boolean AmbassadorExist(String email)
    {
        return findAmbassador(email)!=null;
    }
    
   
    public static Ambassador getAmbassador(Member id)
    {
        Ambassador result = new Ambassador();
        
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   a FROM Ambassador a " +
                "WHERE a.member =:id"
                ;
      
        TypedQuery<Ambassador> q = em.createQuery(qString, Ambassador.class);
        
        q.setParameter("id",id);
        
       try {
            result = q.getSingleResult();
            
        } catch (NoResultException e) {
            
            em.close();
            return null;
        }
       finally{
           em.close();
       }
        return result;
       
    }

    

    public static void updateAmbassador(String todo,Member old, Ambassador nuprof)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Ambassador previous = new Ambassador();
        trans.begin();        
        try {
            previous = em.find(Ambassador.class, old.getId());
            
            Ambassador nu = em.merge(previous);
            
            switch (todo)
            {
                case "contact":
                {
                    editContact(nu,nuprof);
                    break;
                }
                 case "about":
                {
                    editAbout(nu,nuprof);
                    break;
                }
            }
            
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
        
        
    }
   public static void editContact(Ambassador nu,Ambassador nuprof)
   {
       nu.setEmail(nuprof.getEmail());
       nu.setPhone(nuprof.getPhone());
   }
   public static void editAbout(Ambassador nu,Ambassador nuprof)
   {
          nu.setPosition(nuprof.getPosition());
          nu.setSchool(nuprof.getSchool());
          nu.setName(nuprof.getName());
   }
    

}
