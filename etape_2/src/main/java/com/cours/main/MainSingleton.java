/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.factory.SingletonFactory;
import com.cours.factory.SingletonFactory.FactorySingletonType;
import com.cours.singletons.AbstractSingleton;

/**
 *
 * @author elhad
 */
public class MainSingleton {

    private static final Log log = LogFactory.getLog(MainSingleton.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    	// Singleton Factory
    	AbstractSingleton singletonCsv = SingletonFactory.getFactory(FactorySingletonType.CSV_SINGLETON_FACTORY);
    	AbstractSingleton singletonJson = SingletonFactory.getFactory(FactorySingletonType.JSON_SINGLETON_FACTORY);
    	AbstractSingleton singletonXml = SingletonFactory.getFactory(FactorySingletonType.XML_SINGLETON_FACTORY);
    	
	}
}
