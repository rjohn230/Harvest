package Model;

import DataBase.DButil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ProfessionalDB {
    
    public static void insertProfessional (Professional prof)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(prof);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Professional findProfessional(String email)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   p FROM Professional p " +
                " WHERE p.email = :email"
                ;
      
        TypedQuery<Professional> q = em.createQuery(qString, Professional.class);
        
        q.setParameter("email",email);
        Professional JohnDoe = new Professional();
              
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
    
    public static boolean ProfessionalExist(String email)
    {
        return findProfessional(email)!=null;
    }
    
    public static Professional getProfession(Member id)
    {
        Professional result = new Professional();
        
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   p FROM Professional p " +
                "WHERE p.member =:id"
                ;
      
        TypedQuery<Professional> q = em.createQuery(qString, Professional.class);
        
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
    public static void updateProfessional(String todo,Member old, Professional nuprof)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Professional previous = new Professional();
        trans.begin();        
        try {
            previous = em.find(Professional.class, old.getId());
            
            Professional nu = em.merge(previous);
            
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
   public static void editContact(Professional nu,Professional nuprof)
   {
       nu.setFirstName(nuprof.getFirstName());
            nu.setLastName(nuprof.getLastName());
            nu.setEmail(nuprof.getEmail());
            nu.setPhone(nuprof.getPhone());
   }
   public static void editAbout(Professional nu,Professional nuprof)
   {
            nu.setAbout(nuprof.getAbout());
            nu.setProfession(nuprof.getProfession());
            nu.setEducation(nuprof.getEducation());
   } 

}
