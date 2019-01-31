/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.process;

import com.cours.entities.Animal;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class ProcessAnimal implements IProcessAnimal {

    private static final Log log = LogFactory.getLog(ProcessAnimal.class);
    private List<Animal> animals = null;
    private final DateFormat dateFormat = null;
    private Object Charsets;
    private Object file;

    @Override
    public List<Animal> loadAnimalsManually() {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy/mm/dd");
        String dobStr = "2018/01/02";
        Date dob;
        ArrayList<Animal> animals = new ArrayList<Animal>();
        try {
            dob = parser.parse(dobStr);
        } catch (ParseException ex) {
            Logger.getLogger(ProcessAnimal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        Animal lion = new Animal("Lion", 200, dob, "fauve", 3, true);
        Animal chevre = new Animal("Chevre", 35, dob, "fauve", 5, false);
        Animal lapin = new Animal("Lapin", 4, dob, "Noir", 1, false);
        Animal maurice = new Animal("maurice", 70, dob, "bleu", 2, true);
        Animal chien = new Animal("chien", 20, dob, "Roux", 4, true);
        Animal dog = new Animal("dog", 40, dob, "dawwwwg", 3, true);
        Animal renard = new Animal("renard", 14, dob, "marrin", 4, true);
        Animal canard = new Animal("coincoin", 3, dob, "vert", 3, false);
        Animal pigeon = new Animal("pigeon", 0.4, dob, "moche", 2, true);
        Animal rat = new Animal("ratatouille", 2, dob, "sale", 3, true);
        Animal poule = new Animal("dingdong", 200, dob, "magnifayk", 2, true);
        animals.add(lion);
        animals.add(chevre);
        animals.add(lapin);
        animals.add(maurice);
        animals.add(chien);
        animals.add(dog);
        animals.add(renard);
        animals.add(canard);
        animals.add(pigeon);
        animals.add(rat);
        animals.add(poule);
        this.animals = animals;
        return animals;
    }

    @Override
    public List<Animal> loadAnimalsFile() {
       return this.loadAnimalsManually();
//       return animals;
    }

    @Override
    public Double calculMoyennePoidsAnimaux() {
        double total = 0;
        double nbAnimals = 0;
        for (Animal animal : this.getAnimals()) {
           total += animal.getPoids();
           nbAnimals ++;
        }
        return total / nbAnimals;
    }

    @Override
    public Double calculEcartTypePoidsAnimaux() {
        return 0.0;
    }

    @Override
    public List<Animal> sortAnimalsById() {
        List<Animal> animals = this.getAnimals();
        Collections.sort(animals,new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                if (a1.getIdAnimal() > a2.getIdAnimal()) {
                    return 1;
                } else if (a1.getIdAnimal() < a2.getIdAnimal()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        this.animals = animals;
        return animals;
   }

    @Override
    public List<Animal> sortAnimalsByName() {
        List<Animal> animals = this.getAnimals();
        Collections.sort(animals,new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                return a1.getNom().compareTo(a2.getNom());
            }
        });
        this.animals = animals;
        return animals;
    }

    @Override
    public List<Animal> sortAnimalsByWeight() {
        List<Animal> animals = this.getAnimals();
        Collections.sort(animals,new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                if (a1.getPoids() > a2.getPoids()) {
                    return 1;
                } else if (a1.getPoids() < a2.getPoids()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        this.animals = animals;
        return animals;
    }

    @Override
    public List<Animal> sortAnimalsByColor() {
        List<Animal> animals = this.getAnimals();
        Collections.sort(animals,new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                return a1.getCouleur().compareTo(a2.getCouleur());
            }
        });
        this.animals = animals;
        return animals;
    }

    @Override
    public void generateFileByName() {
    }

    @Override
    public void generateFileByWeight() {
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }
}
