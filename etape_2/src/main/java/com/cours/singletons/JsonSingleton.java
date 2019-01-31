/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.singletons;

import com.cours.entities.Personne;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author ElHadji
 * 
 **/
public class JsonSingleton extends AbstractSingleton {

	final String personnesJsonPathFile = "personnesJson.json";

	private JsonSingleton() {
		this.personnes = new ArrayList<Personne>();
		extractPersonnesDatas();
	}

	private static class LazyHolder {
		static final JsonSingleton PersonneSingleton = new JsonSingleton();
	}

	public static synchronized JsonSingleton getInstance() {
		return LazyHolder.PersonneSingleton;
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
				person.setPoids((Long) jsonObjectPerson.get("poids"));
			}
			if (jsonObjectPerson.get("taille") != null) {
				person.setTaille((Long) jsonObjectPerson.get("taille"));
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
	protected void extractPersonnesDatas() {

		try {
			// read the json file
			FileReader reader = new FileReader(personnesJsonPathFile);

			JSONParser jsonParser = new JSONParser();
			JSONObject jSONObject = (JSONObject) jsonParser.parse(reader);

			// get a String from the JSON object
			JSONArray personnesJson = (JSONArray) jSONObject.get("personnes");

			for (int i = 0; i < personnesJson.size(); i++) {

				JSONObject jsonObjectPerson = (JSONObject) personnesJson.get(i);
				Personne person = this.createPersonneWithFileObject(jsonObjectPerson);
				
				if (person != null) {
					this.personnes.add(person);					
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(personnes);

	}
}