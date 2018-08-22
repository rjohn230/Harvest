package DataBase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DButil {
 private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Har");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
