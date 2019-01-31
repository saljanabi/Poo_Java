/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.entities;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author elhad
 */
public class Animal {
    private int idAnimal;
    private String nom;
    private double poids;
    private java.util.Date dateNaissance;
    private String couleur;
    private int nombrePattes;
    private boolean estCarnivore;
    
    private void marcher() {
        System.out.println("L'animal " + this.nom + " marche avec " + this.nombrePattes + " pattes.");
    }
    private void description() {
        System.out.println("L'animal " + this.nom + " est né le " + this.dateNaissance.toString() 
                + ", il pèse " + this.poids + ", il est de couleur " + this.couleur + "il a " + this.nombrePattes +
                        (this.estCarnivore ? ", il est carnivore." : "il n'est pas carnivore." ));
    }
    
    @Override
    public String toString() {
        return this.idAnimal + ", " + this.nom + ", " + this.poids + ", " + this.dateNaissance + ", " + this.couleur + ", " + this.nombrePattes + ", " + this.estCarnivore;
   }

    @Override
    public int hashCode() {
        return this.idAnimal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        return true;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNombrePattes() {
        return nombrePattes;
    }

    public void setNombrePattes(int nombrePattes) {
        this.nombrePattes = nombrePattes;
    }

    public boolean isEstCarnivore() {
        return estCarnivore;
    }

    public void setEstCarnivore(boolean estCarnivore) {
        this.estCarnivore = estCarnivore;
    }

    public Animal(String nom, double poids, Date dateNaissance, String couleur, int nombrePattes, boolean estCarnivore) {
        Random rand = new Random(); 
        this.nom = nom;
        this.poids = poids;
        this.dateNaissance = dateNaissance;
        this.couleur = couleur;
        this.nombrePattes = nombrePattes;
        this.estCarnivore = estCarnivore;
        this.idAnimal = rand.nextInt(400);
    }
    
    
    
}
