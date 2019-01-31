/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.process;

import com.cours.entities.Animal;
import java.util.List;

/**
 *
 * @author elhad
 */
public interface IProcessAnimal {

    public List<Animal> loadAnimalsManually();

    public List<Animal> loadAnimalsFile();

    public Double calculMoyennePoidsAnimaux();

    public Double calculEcartTypePoidsAnimaux();

    public List<Animal> sortAnimalsById();

    public List<Animal> sortAnimalsByName();

    public List<Animal> sortAnimalsByWeight();

    public List<Animal> sortAnimalsByColor();

    public void generateFileByName();

    public void generateFileByWeight();

    public List<Animal> getAnimals();
}
