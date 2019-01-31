/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.singletons;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.cours.entities.Personne;

/**
 *
 * @author ElHadji
 */
public class CsvSingleton extends AbstractSingleton {

    final String personnesCsvPathFile = "personnesCsv.csv";

    private CsvSingleton() {
		this.personnes = new ArrayList<Personne>();
		extractPersonnesDatas();			
    }
    
	private static class LazyHolder {
        static final CsvSingleton PersonneSingleton = new CsvSingleton();
    }
	
	public static synchronized CsvSingleton getInstance() {
		return LazyHolder.PersonneSingleton;
	}

    private Personne createPersonneWithFileObject(String[] attributs) {

    	Personne person = new Personne();

    	if (attributs.length != 8) {
    		return null;
    	}
    	
    	try {
			if (attributs[0] != null) {
				try {
					person.setIdPersonne(Integer.valueOf(attributs[0]));	
				} catch (Exception ex) {
			           ex.printStackTrace();
		        }
			}
			if (attributs[1] != null) {
				try {
					person.setPrenom((String.valueOf(attributs[1])));
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
			if (attributs[2] != null) {
				try {
					person.setNom(String.valueOf(attributs[2]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[3] != null) {
				try {
					person.setPoids(Double.valueOf(attributs[3]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[4] != null) {
				try {
					person.setTaille(Double.valueOf(attributs[4]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (attributs[5] != null) {
				try {
					person.setRue(String.valueOf(attributs[5]));
				} catch (Exception e) {
					e.printStackTrace();
				}    		
			}
			if (attributs[6] != null) {
				try {
					person.setVille(String.valueOf(attributs[6]));
				} catch (Exception e) {
					e.printStackTrace();
				}		    		
			}
			if (attributs[7] != null) {
				try {
					person.setCodePostal(String.valueOf(attributs[7]));
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		} catch (Exception ex) {
	           ex.printStackTrace();
        }
		    	
    	return person;

	}

    @Override
    protected void extractPersonnesDatas() {
    	
		try {
			// read the csv file
			FileReader reader = new FileReader(personnesCsvPathFile);
			// scan the reader
			Scanner scanner = new Scanner(reader);
			// indexation à [1]
        	String Line = scanner.nextLine();	
        	
	        while (scanner.hasNextLine())
	        {
	        	Line = scanner.nextLine();
	        	Line = Line.replace("; ", ";");
	        	Personne person = this.createPersonneWithFileObject(Line.split("\\;"));
	        	if (person != null) {
	        		this.personnes.add(person);	        		
	        	}
	        }
	        
	        scanner.close();
		        
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(personnes);	
    }
}


















