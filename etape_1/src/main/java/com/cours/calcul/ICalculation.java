/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.calcul;

/**
 *
 * @author elhad
 */
public interface ICalculation {

    public int addition(int value1, int value2);

    public int verifyParite(String chaine);

    public int compareChaines(String firstChaine, String secondeChaine);

    public int factorielItterative(int number);

    public int factorielRecursive(int number);

    public int nombreMagique(int[] array);

    public int[] initMyArray(int taille);

    public int[] sortMyArray(int[] arrayToSort);
}
