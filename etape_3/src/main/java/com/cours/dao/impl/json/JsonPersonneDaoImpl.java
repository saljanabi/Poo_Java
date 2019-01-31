package com.cours.dao.impl.json;

import com.cours.dao.IPersonneDao;
import com.cours.entities.Personne;
import com.cours.observer.MySubjectObserver;
import com.cours.utils.Constants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonPersonneDaoImpl /* extends AbstractJsonDao<Personne> */ implements IPersonneDao {

	private final String personnesJsonPathFile = Constants.PERSONNES_JSON_PATH_FILE;
	private MySubjectObserver subject = null;
	private boolean sendNotification = true;

	public JsonPersonneDaoImpl() {
	}

	public JsonPersonneDaoImpl(MySubjectObserver subject) {
		// super(Personne.class, subject, Constants.PERSONNES_JSON_PATH_FILE);
		this.subject = subject;
	}

	@Override
	public Personne findById(int idPersonne) {
		List<Personne> personnes = findAll();
		int i = 1;

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

		List<Personne> personnes = findAll();
		int i = -1;

		for (Personne personComp : personnes) {
			++i;

					
			if (person.getPrenom().equals(personComp.getPrenom()) && person.getNom().equals(personComp.getNom())
					&& person.getPoids() == personComp.getPoids() && person.getTaille() == personComp.getTaille()
					&& person.getRue().equals(personComp.getRue()) && person.getVille().equals(personComp.getVille())
					&& person.getCodePostal().equals(personComp.getCodePostal())) {

				personnes.remove(i);
				this.generateNewFileContent(personnes);
				return true;
			}
		}
		return false;
	}

	private Personne createPersonneWithFileObject(JSONObject jsonObjectPerson) {

		Personne person = new Personne();

		if (jsonObjectPerson == null) {
			return null;
		}

		try {
			if (jsonObjectPerson.get("id") != null) {
				person.setIdPersonne((int) (long) jsonObjectPerson.get("id"));
			} else {
				return null;
			}

			if (jsonObjectPerson.get("prenom") != null) {
				person.setPrenom((String) jsonObjectPerson.get("prenom"));
			}
			if (jsonObjectPerson.get("nom") != null) {
				person.setNom((String) jsonObjectPerson.get("nom"));
			}
			if (jsonObjectPerson.get("poids") != null) {
				person.setPoids((Double) jsonObjectPerson.get("poids"));
			}
			if (jsonObjectPerson.get("taille") != null) {
				person.setTaille((Double) jsonObjectPerson.get("taille"));
			}
			if (jsonObjectPerson.get("rue") != null) {
				person.setRue((String) jsonObjectPerson.get("rue"));
			}
			if (jsonObjectPerson.get("ville") != null) {
				person.setVille((String) jsonObjectPerson.get("ville"));
			}
			if (jsonObjectPerson.get("codePostal") != null) {
				person.setCodePostal((String) jsonObjectPerson.get("codePostal"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return person;
	}

	@Override
	public List<Personne> findAll() {

		List<Personne> personnes = new ArrayList<Personne>();

		try {
			FileReader reader = new FileReader(personnesJsonPathFile);
			JSONParser jsonParser = new JSONParser();
			JSONObject jSONObject = (JSONObject) jsonParser.parse(reader);
			JSONArray personnesJson = (JSONArray) jSONObject.get("personnes");

			for (int i = 0; i < personnesJson.size(); i++) {

				JSONObject jsonObjectPerson = (JSONObject) personnesJson.get(i);
				Personne person = this.createPersonneWithFileObject(jsonObjectPerson);

				if (person != null) {
					personnes.add(person);
				}
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

	private List<Personne> generateNewFileContent(List<Personne> listPersonne) {
		
		JSONArray personnes = new JSONArray();

		for (Personne person : listPersonne) {
			personnes.add(person.getJSONObject());
		}

		HashMap<String,Object> result = new HashMap<String,Object>();
	    result.put("personnes", personnes);

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(personnesJsonPathFile);
			fileWriter.append(new JSONObject(result).toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listPersonne;		
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
