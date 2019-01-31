/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.main;

import com.cours.calcul.Calculation;
import com.cours.calcul.ICalculation;
import com.cours.process.IProcessAnimal;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);
    private static ICalculation calculation = null;
    private static IProcessAnimal processAnimal = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // testVerifyParite();
        //testCompareChaines();
        testFactorielItterative();
        testFactorielRecursive();
        //testNombreMagique();
         //testSortMyArray();
         //testProcessAnimal();*/

    }

    public static void testVerifyParite() {
        Calculation calc = new Calculation();
        Scanner verify = new Scanner(System.in);
        System.out.println("Entrez le nombre entier de votre choix : ");
        String str = verify.nextLine();    
        
        int numberType = calc.verifyParite(str);
        
       if (numberType == 0) {
            System.out.println("Le nombre vaut zéro");
        } else if (numberType == 1) {
            System.out.println("Le nombre est positif et pair");
        } else if (numberType == 2) {
            System.out.println("Le nombre est négatif et pair");
        } else if (numberType == 3) {
            System.out.println("Le nombre est impair et positif");
        } else if (numberType == 4) {
            System.out.println("Le nombre est négatif et impair");
        } else if (numberType == 5) {
            System.out.println("Vous n'avez pas entré un nombre. Veuillez recommencer.");
        }
    }

    public static void testCompareChaines() {
        Calculation calc = new Calculation();
        Scanner verify = new Scanner(System.in);
        System.out.println("Entrez une première chaîne de caractères de votre choix :");
        String str1 = verify.nextLine();    
        
        System.out.println("Entrez une seconde chaîne de caractères de votre choix :");
        String str2 = verify.nextLine();
  
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        
        int strCmp = calc.compareChaines(str1, str2);
        
        if (strCmp == 0) {
            System.out.println("Les deux chaînes sont identiques.");
        } else if (strCmp == 1) {
            System.out.println("La première chaîne est supérieure à la deuxième.");
        } else if (strCmp == -1) {
            System.out.println("La deuxième chaîne est supérieure à la première.");
        }
    }

    /**
     * Attention pour simplifier l'exercice on ne mettre que de petit nombre
     * entier inferieur à 17. Sinon on risque de depasser la limite prévu pour
     * le type int. Vous pouvez donc utiliser le type entier int.
     */
    public static void testFactorielItterative() {
        Calculation calc = new Calculation();
        int i = 12;
        int factoriel = calc.factorielItterative(i);
        System.out.println(i + "! = " + factoriel);
    }

    /**
     * Attention pour simplifier l'exercice on ne mettre que de petit nombre
     * entier inferieur à 17. Sinon on risque de depasser la limite prévu pour
     * le type int. Vous pouvez donc utiliser le type entier int.
     */
    public static void testFactorielRecursive() {
        Calculation calc = new Calculation();
        int i = 5;
        int factoriel = calc.factorielRecursive(i);
        System.out.println(i + "! = " + factoriel);
    }

    public static void testNombreMagique() {
        Calculation calc = new Calculation();
        
        int[] test = calc.initMyArray(10);
        System.out.println(Arrays.toString(test));
        int res = calc.nombreMagique(test);
        System.out.println(res);        
    }

    public static void testSortMyArray() {
        Calculation calc = new Calculation();
        
        int[] test = calc.initMyArray(10);
        System.out.println(Arrays.toString(test));
        int[] res = calc.sortMyArray(test);
        System.out.println(Arrays.toString(res));
    }

    public static void testProcessAnimal() {
        processAnimal.loadAnimalsManually();
        processAnimal.loadAnimalsFile();
        processAnimal.calculMoyennePoidsAnimaux();
        processAnimal.calculEcartTypePoidsAnimaux();
    }
}
