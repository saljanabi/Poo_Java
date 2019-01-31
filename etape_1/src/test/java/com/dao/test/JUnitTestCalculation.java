package com.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.calcul.Calculation;
import com.cours.calcul.ICalculation;
import java.util.Arrays;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTestCalculation {

    private static final Log log = LogFactory.getLog(JUnitTestCalculation.class);
    private static ICalculation calculation = null;

    @BeforeClass
    public static void init() throws Exception {
        // configuration de l'application
        log.debug("Entree de la methode");
        calculation = new Calculation();
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitAddition() {
        log.debug("Entree de la methode");
        Assert.assertEquals(2, calculation.addition(1, 1));
        Assert.assertEquals(10, calculation.addition(8, 2));
        Assert.assertEquals(20, calculation.addition(10, 10));
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitVerifyParite() {
        log.debug("Entree de la methode");
        Assert.assertEquals(0, calculation.verifyParite("0"));
        Assert.assertEquals(1, calculation.verifyParite("2"));
        Assert.assertEquals(2, calculation.verifyParite("-4"));
        Assert.assertEquals(3, calculation.verifyParite("3"));
        Assert.assertEquals(4, calculation.verifyParite("-3"));
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitCompareChaines() {
        log.debug("Entree de la methode");
        Assert.assertEquals(0, calculation.compareChaines("chat", "chat"));
        Assert.assertEquals(-1, calculation.compareChaines("chat", "chbt"));
        Assert.assertEquals(1, calculation.compareChaines("chot", "chat"));
        Assert.assertEquals(-1, calculation.compareChaines("chat", "chien"));
        Assert.assertEquals(0, calculation.compareChaines("chien", "chien"));
        Assert.assertEquals(1, calculation.compareChaines("lion", "chat"));
        Assert.assertEquals(0, calculation.compareChaines("lion", "lion"));
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitFactorielItterative() {
        log.debug("Entree de la methode");
        Assert.assertEquals(1, calculation.factorielItterative(0));
        Assert.assertEquals(120, calculation.factorielItterative(5));
        Assert.assertEquals(40320, calculation.factorielItterative(8));
        Assert.assertEquals(3628800, calculation.factorielItterative(10));
        Assert.assertEquals(2004189184, calculation.factorielItterative(16));
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitFactorielRecursive() {
        log.debug("Entree de la methode");
        Assert.assertEquals(1, calculation.factorielRecursive(0));
        Assert.assertEquals(120, calculation.factorielRecursive(5));
        Assert.assertEquals(40320, calculation.factorielRecursive(8));
        Assert.assertEquals(3628800, calculation.factorielRecursive(10));
        Assert.assertEquals(2004189184, calculation.factorielRecursive(16));
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitNombreMagique() {
        log.debug("Entree de la methode");
        int[] myArray = calculation.initMyArray(100);
        log.debug("myArray not sorted : " + Arrays.toString(myArray));
        int nombreMagique = calculation.nombreMagique(myArray);
        log.debug("nombreMagique : " + nombreMagique);
        Arrays.sort(myArray);
        log.debug("myArray sorted : " + Arrays.toString(myArray));
        Assert.assertEquals((myArray[0] + myArray[myArray.length - 1]) / 2, nombreMagique);
        log.debug("Sortie de la methode");
    }

    @Test
    public void testJUnitSortMyArray() {
        log.debug("Entree de la methode");
        int[] myArray = calculation.initMyArray(100);
        myArray = calculation.sortMyArray(myArray);
        for (int i = 0; i < myArray.length; i++) {
            if (i < myArray.length - 1) {
                Assert.assertTrue(myArray[i + 1] >= myArray[i]);
            }
        }
        log.debug("Sortie de la methode");
    }

    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        calculation = null;
        log.debug("Sortie de la methode");
    }
}
