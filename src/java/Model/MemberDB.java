package Model;

import DataBase.DButil;
import static Model.ProfessionalDB.editAbout;
import static Model.ProfessionalDB.editContact;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class MemberDB {
    
    public static void insertMember (Member member)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(member);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static Member findMember(long id)
    {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Member JohnDoe = new Member();
        trans.begin();        
        try {
            JohnDoe = em.find(Member.class, id);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
        
        return JohnDoe;
        
    }
    
    public static boolean MemeberPasswordMataches(String password,Member member)
    {
        String salted = member.getPassSalt();
        String salt = member.getSalt();
       
        
        return salted.equals(password+salt);
    }
    
    public static List<Professional> searchProfessional(String[]search )
    {
        List<Professional> result = new ArrayList<>();
        
        EntityManager em = DButil.getEmFactory().createEntityManager();
        String qString = "SELECT p FROM Member m " +
                "JOIN Professional p JOIN Locale l JOIN Subject s WHERE p.profession = :profession"
                ;
        
         qString = qString.concat(appendSubjectsToSearch(search));
        
        TypedQuery<Professional> q = em.createQuery(qString, Professional.class);
        q.setParameter("profession", search[0]);
        if(qString.contains(":cs")) q.setParameter("cs", search[1]);
        if(qString.contains(":med"))q.setParameter("med", search[2]);
        if(qString.contains(":sci"))q.setParameter("sci", search[3]);
        if(qString.contains(":egr"))q.setParameter("egr", search[4]);
        if(qString.contains(":bus"))q.setParameter("bus", search[5]);
        if(qString.contains(":law"))q.setParameter("law", search[6]);
        
        
        
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            System.out.print(e);
        } 
       
       if(result.isEmpty())
       {
             qString = "SELECT p FROM Member m " +
                "JOIN Professional p JOIN Locale l JOIN Subject s WHERE l.city = :city";
        qString = qString.concat(appendSubjectsToSearch(search));
        
        q = em.createQuery(qString, Professional.class);
        q.setParameter("city", search[0]);
        if(qString.contains(":cs")) q.setParameter("cs", search[1]);
        if(qString.contains(":med"))q.setParameter("med", search[2]);
        if(qString.contains(":sci"))q.setParameter("sci", search[3]);
        if(qString.contains(":egr"))q.setParameter("egr", search[4]);
        if(qString.contains(":bus"))q.setParameter("bus", search[5]);
        if(qString.contains(":law"))q.setParameter("law", search[6]);
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            System.out.print(e);
        } 
       }
       
       
       if(result.isEmpty())
       {
             qString = "SELECT p FROM Member m " +
                "JOIN Professional p JOIN Locale l JOIN Subject s WHERE l.state = :state"
                ;
         qString = qString.concat(appendSubjectsToSearch(search));
        
        q = em.createQuery(qString, Professional.class);
        q.setParameter("state", search[0]);
        if(qString.contains(":cs")) q.setParameter("cs", search[1]);
        if(qString.contains(":med"))q.setParameter("med", search[2]);
        if(qString.contains(":sci"))q.setParameter("sci", search[3]);
        if(qString.contains(":egr"))q.setParameter("egr", search[4]);
        if(qString.contains(":bus"))q.setParameter("bus", search[5]);
        if(qString.contains(":law"))q.setParameter("law", search[6]);
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            System.out.print(e);
        } 
       
       }
       
        if(result.isEmpty())
       {
             qString = "SELECT p FROM Member m " +
                "JOIN Professional p JOIN Locale l JOIN Subject s WHERE l.country = :country"
                ; 
       qString = qString.concat(appendSubjectsToSearch(search));
        
        q = em.createQuery(qString, Professional.class);
        q.setParameter("country", search[0]);
        if(qString.contains(":cs")) q.setParameter("cs", search[1]);
        if(qString.contains(":med"))q.setParameter("med", search[2]);
        if(qString.contains(":sci"))q.setParameter("sci", search[3]);
        if(qString.contains(":egr"))q.setParameter("egr", search[4]);
        if(qString.contains(":bus"))q.setParameter("bus", search[5]);
        if(qString.contains(":law"))q.setParameter("law", search[6]);
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            System.out.print(e);
        } 
       }
       
        if(result.isEmpty())
       {
             qString = "SELECT p FROM Member m " +
                "JOIN Professional p JOIN Locale l JOIN Subject s WHERE p.firstName= p.firstName "
                ; 
       qString = qString.concat(appendSubjectsToSearch(search));
       boolean exit = appendSubjectsToSearch(search).isEmpty();
       
       if(exit)
       {
           // do nothing;
       }
       else{
        q = em.createQuery(qString, Professional.class);
        if(qString.contains(":cs")) q.setParameter("cs", search[1]);
        if(qString.contains(":med"))q.setParameter("med", search[2]);
        if(qString.contains(":sci"))q.setParameter("sci", search[3]);
        if(qString.contains(":egr"))q.setParameter("egr", search[4]);
        if(qString.contains(":bus"))q.setParameter("bus", search[5]);
        if(qString.contains(":law"))q.setParameter("law", search[6]);
       try {
            result = q.getResultList();
            
        } catch (NoResultException e) {
            System.out.print(e);
        } finally {
            em.close();
        }
       }
       }
        
        List<Professional> resolve = new ArrayList<>();
        
        for(Professional fix : result)
        {
            if(!resolve.contains(fix))
            {
                resolve.add(fix);
            }
            
            
        }
        
        return resolve;
        
    }
    
    public static String appendSubjectsToSearch(String[] inputs)
    {
        String append="";
        for(int i= 1;i<inputs.length;i++)
        {
            if( inputs[i]!=null)
            {
                if(inputs[i].equalsIgnoreCase("on"))
                {
                switch (i)
                {
                    case 1:
                    {
                        append = append.concat(" AND s.cs = :cs ");
                        break;
                    }
                    case 2:
                    {
                       append= append.concat(" AND s.med = :med ");
                        break;
                    }
                    case 3:
                    {
                       append= append.concat(" AND s.sci = :sci ");
                        break;
                    }
                    case 4:
                    {
                        append=append.concat(" AND s.egr = :egr ");
                        break;
                    }
                    case 5:
                    {
                        append = append.concat(" AND s.bus = :bus ");
                        break;
                    }
                    case 6:
                    {
                        append = append.concat(" AND s.law = :law ");
                        break;
                    }
                }
                }
            }
        }
            return append;
    }

    public static void updatePassword(String nuPassword,long member) {
        EntityManager em = DButil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        Member previous = new Member();
        trans.begin();        
        try {
            previous = em.find(Member.class, member);
            
            Member nu = em.merge(previous);
            nu.setPasswordSalted(nuPassword);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
        
        
        
    }
    
    
    
    
    
    
    

}
