/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.singletons;

import com.cours.entities.Personne;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 *
 * @author ElHadji
 */
public class XmlSingleton extends AbstractSingleton {

	final String personnesXmlPathFile = "personnesXml.xml";

	private XmlSingleton() {
		this.personnes = new ArrayList<Personne>();
		extractPersonnesDatas();
	}

	private static class LazyHolder {
		static final XmlSingleton PersonneSingleton = new XmlSingleton();
	}

	public static synchronized XmlSingleton getInstance() {
		return LazyHolder.PersonneSingleton;
	}


	public Personne createPersonneWithFileObject(Element elementPersonne) {

		Personne person = new Personne();


		try {
			if (elementPersonne.getAttribute("id") != null) {
				try {
					person.setIdPersonne(Integer.valueOf(elementPersonne.getAttribute("id")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (elementPersonne.getElementsByTagName("prenom") != null && elementPersonne.getElementsByTagName("prenom").item(0) != null) {
				try {
					person.setPrenom(elementPersonne.getElementsByTagName("prenom").item(0).getTextContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (elementPersonne.getElementsByTagName("nom") != null && elementPersonne.getElementsByTagName("nom").item(0) != null) {
				try {
					person.setNom(elementPersonne.getElementsByTagName("nom").item(0).getTextContent());
				} catch (Exception e) {
					e.printStackTrace();
				}					
			}

			if (elementPersonne.getElementsByTagName("poids") != null && elementPersonne.getElementsByTagName("poids").item(0) != null) {
				try {
					person.setPoids(Double.valueOf(elementPersonne.getElementsByTagName("poids").item(0).getTextContent()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (elementPersonne.getElementsByTagName("taille") != null && elementPersonne.getElementsByTagName("taille").item(0) != null) {
				try {
					person.setTaille(Double.valueOf(elementPersonne.getElementsByTagName("taille").item(0).getTextContent()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (elementPersonne.getElementsByTagName("rue") != null && elementPersonne.getElementsByTagName("rue").item(0) != null) {
				try {
					person.setRue(elementPersonne.getElementsByTagName("rue").item(0).getTextContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (elementPersonne.getElementsByTagName("ville") != null && elementPersonne.getElementsByTagName("ville").item(0) != null) {
				try {
					person.setVille(elementPersonne.getElementsByTagName("ville").item(0).getTextContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (elementPersonne.getElementsByTagName("codePostal") != null && elementPersonne.getElementsByTagName("codePostal").item(0) != null) {
				try {
					person.setCodePostal(elementPersonne.getElementsByTagName("codePostal").item(0).getTextContent());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		}

		return person;
	}

	@Override
	protected void extractPersonnesDatas() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// create a new document from file
			File file = new File(personnesXmlPathFile);
			
			Document document;
			try {
				document = (Document) builder.parse(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			document.getDocumentElement().normalize();
			
			//Element elements = (Element) document.getDocumentElement();

			// get all tag names
			NodeList tags = document.getElementsByTagName("personne");

			// print the text content of each child
			for (int i = 0; i < tags.getLength(); i++) {
				Personne person = createPersonneWithFileObject((Element) tags.item(i));
				this.personnes.add(person);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(personnes);
	}

}
