/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.calcul;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.Random;

/**
 *
 * @author elhad
 */
public class Calculation implements ICalculation {

    @Override
    public int addition(int value1, int value2) {
        return value1 + value2;
    }

    @Override
    public int[] initMyArray(int taille) {
        Random rand = new Random();        
        int[] array = new int[taille];
        for (int i = 0; i < taille; i++) {
            array[i] = rand.nextInt();
        }        
        return array;
    }

    @Override
    public int[] sortMyArray(int[] arrayToSort) {
        boolean swapped = true;
        int j = 0;
        int permute;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < arrayToSort.length - j; i++) {
                if (arrayToSort[i] > arrayToSort[i + 1]) {
                    permute = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[i + 1];
                    arrayToSort[i + 1] = permute;
                    swapped = true;
                }
            }
        }
        return arrayToSort;
    }

    @Override
    public int factorielItterative(int number) {
        int factoriel = 1;
         if (number <= 1) {
            return 1;   
        }
        for (int i = 1; i <= number; i++) {
            factoriel = factoriel * i;
        }
        return factoriel;
    }

    @Override
    public int factorielRecursive(int number) {
        if (number <= 1) {
            return 1;   
        } else {
            return number * factorielRecursive(number - 1);
        }
    }

    /**
     * renvois 0 si les deux chaines de caractères sont identiques, 1 si la
     * premiere chaine est supérieur à la seconde chaine (en terme de code
     * ASCII), -1 si la premiere chaine est inférieur à la seconde chaine (en
     * terme de code ASCII)
     *
     * @param firstChaine
     * @param secondeChaine
     * @return result
     */
    @Override
    public int compareChaines(String firstChaine, String secondeChaine) {
        int firstChaineLength = firstChaine.length();
        int secondeChaineLength = secondeChaine.length();
        
        for (int i = 0; i < min(firstChaineLength, secondeChaineLength); i++) {
            if(firstChaine.charAt(i) > secondeChaine.charAt(i)) {
                return 1;
            }
            if(firstChaine.charAt(i) < secondeChaine.charAt(i)) {
                return -1;
            }
        }
        
        if(firstChaineLength > secondeChaineLength) {
            return 1;
        } else if(firstChaineLength < secondeChaineLength) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * renvois 0 si le nombre est égale à 0, 1 s’il est pair et positif, 2 s’il
     * est négatif et paire, 3 s’il est impair et positif et 4 s’il est négatif
     * et impair
     *
     * @param chaine
     * @return result
     */
    @Override
    public int verifyParite(String chaine) {
        int i;
        
        try {
        i = Integer.parseInt(chaine);
        } catch (Exception e) {
            return 5;
        }   
        System.out.println(i);
        
        if (i == 0) {
            return 0;
        } else if (i%2 == 0 && i > 0) {
            return 1;
        } else if (i%2 == 0 && i < 0) {
            return 2;
        } else if (i%2 != 0 && i > 0) {
            return 3;
        }
            return 4;
    }

    @Override
    public int nombreMagique(int[] array) {
        int min = array[0];
        int max = array[0];

        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
               min = array[i];
            } 
            if (max < array[i]) {
                max = array[i];
            }
        }
        return (min + max) / 2;
    }
}
