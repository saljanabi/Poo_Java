package com.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.process.IProcessAnimal;
import com.cours.process.ProcessAnimal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTestProcessAnimal {

    private static final Log log = LogFactory.getLog(JUnitTestProcessAnimal.class);
    private static IProcessAnimal processAnimal = null;

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        processAnimal = new ProcessAnimal();
        testJUnitLoadAnimalsManually();
        processAnimal.getAnimals().clear();
//        testJUnitLoadAnimalsFile();
        log.debug("Sortie de la methode");
    }

    public static void testJUnitLoadAnimalsManually() {
        log.debug("Entree de la methode");
        Assert.assertEquals(11, processAnimal.loadAnimalsManually().size());
        log.debug("Sortie de la methode");
    }

    public static void testJUnitLoadAnimalsFile() {
        log.debug("Entree de la methode");
        Assert.assertEquals(11, processAnimal.loadAnimalsFile().size());
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitCalculMoyenneEcartTypePoidsAnimaux() {
        log.debug("Entree de la methode");
        log.debug("calculMoyennePoidsAnimaux : " + processAnimal.calculMoyennePoidsAnimaux());
        log.debug("calculEcartTypePoidsAnimaux : " + processAnimal.calculEcartTypePoidsAnimaux());
        log.debug("difference moyenne : " + Math.abs(122.3636 - processAnimal.calculMoyennePoidsAnimaux()));
        log.debug("difference ecartType : " + Math.abs(195.7327 - processAnimal.calculEcartTypePoidsAnimaux()));
        Assert.assertTrue(Math.abs(122.3636 - processAnimal.calculMoyennePoidsAnimaux()) < 1e-4);
        Assert.assertTrue(Math.abs(195.7327 - processAnimal.calculEcartTypePoidsAnimaux()) < 1e-4);
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        processAnimal = null;
        log.debug("Sortie de la methode");
    }
}
