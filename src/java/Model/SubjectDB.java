package Model;

import DataBase.DButil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class SubjectDB {
   public static void insertMember (Subject subjects)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(subjects);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
   
   public static Subject getSubject(Member id)
    {
        Subject result = new Subject();
        
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT   s FROM Subject s " +
                "WHERE s.member =:id"
                ;
      
        TypedQuery<Subject> q = em.createQuery(qString, Subject.class);
        
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
   public static void updateSubjects(long member, Subject sub)
   {
       EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Subject previous = new Subject();
        trans.begin();        
        try {
            previous = em.find(Subject.class, member);
            
            Subject nu = em.merge(previous);
            nu.setBus(sub.getBus());
            nu.setCS(sub.getCS());
            nu.setEgr(sub.getEgr());
            nu.setLaw(sub.getLaw());
            nu.setMed(sub.getMed());
            nu.setSci(sub.getSci());
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
   }
}
