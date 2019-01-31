package com.cours.entities;

import java.util.HashMap;

import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ElHadji
 */
public class Personne {
    private int idPersonne;
    private String prenom;
    private String nom;
    private double poids;
    private double taille;
    private String rue;
    private String ville;
    private String codePostal;
    
    @Override
    public String toString() {
        return this.idPersonne + ", " + this.prenom + ", " + this.nom + ", " + this.poids + ", " + this.taille + ", " + this.rue + ", " + this.ville + ", " + this.codePostal + "\n";
   }

    @Override
    public int hashCode() {
        return this.idPersonne;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (idPersonne == ((Personne)obj).hashCode()) {
        	return true;
        }
        return false;
    }
    
    public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	// IMC calculation
    public double getImc() {
        return (poids / Math.pow(taille, 2.0));
    }
    //get and set taille and poids
	public double getTaille() {
		return taille;
	}
	
	public void setTaille(double taille) {
		this.taille = taille;
	}
	
	public double getPoids() {
		return poids;
	}
	
	public void setPoids(double poids) {
		this.poids = poids;
	}
    // interprétation de l'IMC avec un booléen
    public boolean isMaigre() {
        return (getImc() >= 16.5 && getImc() < 18.5);
    }
	
    public boolean isSurPoids() {
        return (getImc() >= 25 && getImc() < 30);
    }
    
    public boolean isObese() {
        return (getImc() >= 30);
    }
    
    public String getCsvString() {
    	
    	return  "\n" +
				getIdPersonne() + "; " + 
				getPrenom() + ";" + 
				getNom() + "; " + 
				String.valueOf(getPoids()) + ";" + 
				String.valueOf(getTaille()) + ";" + 
				getRue() + "; " +
				getVille() + ";" +
				getCodePostal();
    }
    
    public JSONObject getJSONObject() {
    	HashMap<String,Object> personMap = new HashMap<String,Object>();
    	
    	personMap.put("id", this.getIdPersonne());
    	personMap.put("prenom", this.prenom);
    	personMap.put("nom", this.nom);
    	personMap.put("poids", this.poids);
    	personMap.put("taille", this.taille);
    	personMap.put("rue", this.rue);
    	personMap.put("ville", this.ville);
    	personMap.put("codePostal", this.codePostal);

    	return new JSONObject(personMap);
    }
}
