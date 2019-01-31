package com.cours.dao.impl.xml;

import com.cours.dao.IPersonneDao;
import com.cours.entities.Personne;
import com.cours.observer.MySubjectObserver;
import com.cours.utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlPersonneDaoImpl /*extends AbstractXmlDao<Personne>*/ implements IPersonneDao {

	private final String personnesXmlPathFile = Constants.PERSONNES_XML_PATH_FILE;
	private MySubjectObserver subject = null;
	private boolean sendNotification = true;

	public XmlPersonneDaoImpl() {
	}

	public XmlPersonneDaoImpl(MySubjectObserver subject) {
		//super(Personne.class, subject, Constants.PERSONNES_XML_PATH_FILE);
		this.subject = subject;
	}

	@Override
	public Personne findById(int idPersonne) {
		List<Personne> personnes = findAll();

		int i = -1;

		for (Personne person : personnes) {
			++i;
			if (idPersonne == person.getIdPersonne()) {
				return person;
			}
		}
		return null;
	}

	@Override
	public Personne create(Personne obj) {

		List<Personne> personnes = findAll();
		int idPersonne = this.generateIdNewPersonne();
		obj.setIdPersonne(idPersonne);

		personnes.add(personnes.size(), obj);
		this.generateNewFileContent(personnes);

		return obj;
	}

	@Override
	public boolean delete(Personne person) {

		// Besoin des objets Personne
		List<Personne> personnes = findAll();
		int i = -1;

		for (Personne personComp : personnes) {
			++i;
			if (person.getPrenom().equals(personComp.getPrenom())
					&& person.getNom().equals(personComp.getNom())
					&& person.getPoids() == personComp.getPoids() 
					&& person.getTaille() == personComp.getTaille()
					&& person.getRue().equals(personComp.getRue())
					&& person.getVille().equals(personComp.getVille())
					&& person.getCodePostal().equals(personComp.getCodePostal())) {

				personnes.remove(i);
				this.generateNewFileContent(personnes);
				return true;
			}
		}
		return false;
	}

	private Personne createPersonneWithFileObject(Element elementPersonne) {

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
	public List<Personne> findAll() {

		List<Personne> personnes = new ArrayList<Personne>();


		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// create a new document from file
			File file = new File(personnesXmlPathFile);

			Document document;
			try {
				document = (Document) builder.parse(file);
				document.getDocumentElement().normalize();
				// get all tag names
				NodeList tags = document.getElementsByTagName("personne");
				// print the text content of each child
				for (int i = 0; i < tags.getLength(); i++) {
					Personne person = createPersonneWithFileObject((Element) tags.item(i));
					personnes.add(person);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return personnes;
	}

	@Override
	public int generateIdNewPersonne() {

		List<Personne> personnes = findAll();
		if (personnes.size() > 0) {			
			Personne lastPerson = personnes.get(personnes.size() - 1);
			return lastPerson.getIdPersonne() + 1;
		} else {
			return 1;
		}

	}

	private List<Personne> generateNewFileContent(List<Personne> obj) {

		String newFileContent = "";

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// Création de la structure xml et de personnes
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("personnes");
			doc.appendChild(rootElement);

			//alimentations de personnes
			for (Personne person : obj) {
				Element personElement = doc.createElement("personne");

				//On set l'id
				Attr id = doc.createAttribute("id");
				id.setValue(Integer.toString(person.getIdPersonne()));
				personElement.setAttributeNode(id);

				//on set les propriétés
				Element prenom = doc.createElement("prenom");
				prenom.appendChild(doc.createTextNode(person.getPrenom()));
				personElement.appendChild(prenom);
				
				Element nom = doc.createElement("nom");
				nom.appendChild(doc.createTextNode(person.getNom()));
				personElement.appendChild(nom);
				
				Element poids = doc.createElement("poids");
				poids.appendChild(doc.createTextNode(Double.toString((person.getPoids()))));
				personElement.appendChild(poids);
				
				Element taille = doc.createElement("taille");
				taille.appendChild(doc.createTextNode(Double.toString((person.getTaille()))));
				personElement.appendChild(taille);
				
				Element rue = doc.createElement("rue");
				rue.appendChild(doc.createTextNode(person.getRue()));
				personElement.appendChild(rue);
				
				Element ville = doc.createElement("ville");
				ville.appendChild(doc.createTextNode(person.getVille()));
				personElement.appendChild(ville);
				
				Element codePostal = doc.createElement("codePostal");
				codePostal.appendChild(doc.createTextNode(person.getCodePostal()));
				personElement.appendChild(codePostal);

				//on ajoute le node PersonElement à RootElement
				rootElement.appendChild(personElement);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(personnesXmlPathFile));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
		return obj;

	}

	@Override
	public boolean sendNotification() {
		return sendNotification;
	}

	@Override
	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}
}
