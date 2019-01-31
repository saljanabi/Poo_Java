/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.factory;

import com.cours.singletons.AbstractSingleton;
import com.cours.singletons.CsvSingleton;
import com.cours.singletons.JsonSingleton;
import com.cours.singletons.XmlSingleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class SingletonFactory {

	public static String className = SingletonFactory.class.getName();
	private static final Log log = LogFactory.getLog(SingletonFactory.class);

	public enum FactorySingletonType {

		CSV_SINGLETON_FACTORY, XML_SINGLETON_FACTORY, JSON_SINGLETON_FACTORY
	}

	/**
	 * @param type
	 * @return AbstractStatisticSingleton
	 */
	public static AbstractSingleton getFactory(FactorySingletonType type) {
		
		if (type == FactorySingletonType.CSV_SINGLETON_FACTORY) {
			return CsvSingleton.getInstance();
		}
		if (type == FactorySingletonType.JSON_SINGLETON_FACTORY) {
			return JsonSingleton.getInstance();
		}
		if (type == FactorySingletonType.XML_SINGLETON_FACTORY) {
			return XmlSingleton.getInstance();
		}
		return null;		
	}
}
