package Model;

import DataBase.DButil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class LocaleDB {
    
    public static void insertMember (Locale location)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(location);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static Locale getLocale(Member id)
    {
        Locale result = new Locale();
        
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   l FROM Locale l " +
                "WHERE l.member =:id"
                ;
      
        TypedQuery<Locale> q = em.createQuery(qString, Locale.class);
        
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

    public static void updateLocation(long id, Locale loco) {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Locale previous = new Locale();
        trans.begin();        
        try {
            previous = em.find(Locale.class, id);
            
            Locale nu = em.merge(previous);
            nu.setCity(loco.getCity());
            nu.setCountry(loco.getCountry());
            nu.setState(loco.getState());
            
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

}
