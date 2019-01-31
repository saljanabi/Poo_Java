package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.observer.MyObserver;
import com.cours.observer.MySubjectObserver;

public abstract class AbstractDaoFactory extends MyObserver {

    public abstract IPersonneDao getPersonneDao();

    public enum FactoryType {

        MANUAL_DAO, CSV_DAO, XML_DAO, JSON_DAO, SQL_DAO
    }

    public static AbstractDaoFactory getDaoFactory(FactoryType type) {
        MySubjectObserver sujet = null;
        CsvDaoFactory csvDaoFactory = null;
        XmlDaoFactory xmlDaoFactory = null;
        JsonDaoFactory jsonDaoFactory = null;
        return null;
    }
}
