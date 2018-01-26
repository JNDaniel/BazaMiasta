/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazamiasta;

import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        EntityManager em = Utils.getEntityManager();
        em.getTransaction().begin();
        Query wojewodztwa =  em.createNamedQuery("Wojewodztwo.findAll");
        List<Wojewodztwo> wojList = (List<Wojewodztwo>) wojewodztwa.getResultList();
        for(Wojewodztwo w : wojList)
        {
            w.getPowiatSet().size(); //sztuczne wywolanie w celu fetchowania danych do setu - ze wzgledu na fetchType LAZY dopiero w tym momencie dane sa zaciagane do setu powiaty
            System.out.println("Wojewodztwo: "+w.getNazwa()+" Liczba powiatow: "+w.getPowiatSet().size()+" Liczba miast: "+w.getMiastoSet().size());
        }
        
        System.out.println("Wprowadz nazwe powiatu");
        Scanner sc = new Scanner(System.in);
        String nazwaPowiatu = sc.nextLine();
        
        Query powiatQuery = em.createNamedQuery("Powiat.findByNazwa");
        powiatQuery.setParameter("nazwa",nazwaPowiatu);
        List<Powiat> powiatRes = powiatQuery.getResultList();
        for(Powiat p : powiatRes)
        {
            if(!p.getMiastoSet().isEmpty())
            {
                System.out.println("Powiat: "+p.getNazwa()+" Wojewodztwo: "+p.getWojewodztwo().getNazwa()+ " Miasta: \n");
            p.getMiastoSet().forEach((m) -> {
                System.out.print(m.getNazwa()+", ");
            });
            }
            else
            {
                System.out.println("Powiat: "+p.getNazwa()+" Wojewodztwo: "+p.getWojewodztwo().getNazwa()+ " brak danych o miastach w bazie \n");
            }
        }
        
//        Query allWoj = em.createNamedQuery("Miasto.findAll");
//        List<Miasto> allWojList = allWoj.getResultList();
//        for(Miasto w : allWojList)
//        {
//            if(w.getWojewodztwo()==null) continue;
//            System.out.println(w.getWojewodztwo());
//        }
        
        em.getTransaction().commit();
        em.close();
    }
}
