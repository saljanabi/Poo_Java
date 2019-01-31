package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.dao.impl.csv.CsvPersonneDaoImpl;
import com.cours.observer.MySubjectObserver;

public class CsvDaoFactory extends AbstractDaoFactory {

    private IPersonneDao personneDao = null;

    public CsvDaoFactory(MySubjectObserver subject) {
        this.personneDao = new CsvPersonneDaoImpl();
    }

    @Override
    public IPersonneDao getPersonneDao() {
        return personneDao;
    }

    @Override
    public void updateSource() {
    }
}
