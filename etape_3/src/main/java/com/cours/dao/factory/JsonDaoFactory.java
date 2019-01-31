package com.cours.dao.factory;

import com.cours.dao.IPersonneDao;
import com.cours.dao.impl.json.JsonPersonneDaoImpl;
import com.cours.observer.MySubjectObserver;

public class JsonDaoFactory extends AbstractDaoFactory {

    private IPersonneDao personneDao = null;

    public JsonDaoFactory(MySubjectObserver subject) {
        this.personneDao = new JsonPersonneDaoImpl();
    }

    @Override
    public IPersonneDao getPersonneDao() {
        return personneDao;
    }

    @Override
    public void updateSource() {
    }
}
