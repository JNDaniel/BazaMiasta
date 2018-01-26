/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazamiasta;


import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel
 */
public class Utils {
    static EntityManager em;
    public static EntityManager getEntityManager() 
    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        em = entityManagerFactory.createEntityManager();
        return em;
    }
   
}
