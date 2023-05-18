package com.epul.permispiste.service;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.repositories.ActionRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ActionService{

    @Autowired
    private ActionRepository uneActionRepository;

    public List<ActionEntity> getAll() throws MonException {
        List<ActionEntity> actions;
        String request = "SELECT a FROM ActionEntity a";

        try {
            Session session = ServiceHibernate.currentSession();
            actions = session.createQuery(request, ActionEntity.class).getResultList();
            session.close();
        } catch (Exception e) {
            throw new MonException("Impossible d'accéder à la SessionFactory: ", e.getMessage());
        }

        return actions;
    }

    public ActionEntity getActionById(String id) {
        ActionEntity action;
        String request = "SELECT a FROM ActionEntity a WHERE a.numaction = " + id;
        try {
            Session session = ServiceHibernate.currentSession();
            List results = session.createQuery(request).getResultList();
            action = results.size() > 0 ? (ActionEntity) results.get(0) : null;
            session.close();
        } catch (Exception e) {
            throw new MonException("Récupération de l'action impossible: ", e.getMessage());
        }

        return action;
    }

    public void insert(ActionEntity actionEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.save(actionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de l'action impossible : ", e.getMessage());
        }
    }

    public void delete(ActionEntity actionEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.delete(actionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de l'action impossible : ", e.getMessage());
        }
    }

    public void update(ActionEntity actionEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.merge(actionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de l'action impossible : ", e.getMessage());
        }
    }

    public Collection<ActionEntity> getActionByObjectif(int id){
        List<ActionEntity> actions;
        String request = "SELECT a FROM ActionEntity a JOIN FETCH a.estAssociesByNumaction e WHERE e.numobjectif =" + id;
        try {
            Session session = ServiceHibernate.currentSession();
            actions = session.createQuery(request, ActionEntity.class).getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            throw new MonException("Impossible d'accèder à la SessionFactory: ",  ex.getMessage());
        }
        return actions;
    }


}
