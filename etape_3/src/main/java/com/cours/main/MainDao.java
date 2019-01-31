
package com.cours.main;

import com.cours.dao.impl.csv.CsvPersonneDaoImpl;
import com.cours.dao.impl.json.JsonPersonneDaoImpl;
import com.cours.dao.impl.xml.XmlPersonneDaoImpl;
import com.cours.entities.Personne;

public class MainDao {

    public static void main(String[] args) {
        // TODO code application logic here
    	
    	CsvPersonneDaoImpl CSVImplPersonne = new CsvPersonneDaoImpl();
    	JsonPersonneDaoImpl JSONImplPersonne = new JsonPersonneDaoImpl();
    	XmlPersonneDaoImpl XMLImplPersonne = new XmlPersonneDaoImpl();
    	
    	Personne sarah = new Personne();
    	sarah.setPrenom("Sarah");
    	sarah.setNom("Baajf");
    	sarah.setPoids(30);
    	sarah.setTaille(30);
    	sarah.setRue("Avenue Victor Hugo");
    	sarah.setVille("Aubervilliers");
    	sarah.setCodePostal("93300");
    	
    	
    	//System.out.println(CSVImplPersonne.create(sarah));
    	//System.out.println(CSVImplPersonne.delete(sarah));
    	
    	//System.out.println(JSONImplPersonne.create(sarah));
    	//System.out.println(JSONImplPersonne.delete(sarah));
    	
//    	System.out.println(XMLImplPersonne.create(sarah));
//    	System.out.println(XMLImplPersonne.delete(sarah));
    	
    	
    }
}
