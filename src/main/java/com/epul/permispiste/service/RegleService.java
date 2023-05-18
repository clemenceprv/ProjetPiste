package com.epul.permispiste.service;

import com.epul.permispiste.domains.RegleEntity;
import com.epul.permispiste.mesExceptions.MonException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RegleService implements IRegleService {
    public List<RegleEntity> getAll() throws MonException {
        List<RegleEntity> regles;
        String request = "SELECT r FROM RegleEntity r";

        try {
            Session session = ServiceHibernate.currentSession();
            regles = session.createQuery(request, RegleEntity.class).getResultList();
            session.close();
        } catch (Exception e) {
            throw new MonException("Impossible d'accéder à la SessionFactory: ", e.getMessage());
        }

        return regles;
    }

    public RegleEntity getRegleById(String id) {
        RegleEntity regle;
        String request = "SELECT r FROM RegleEntity r WHERE r.id = " + id;
        try {
            Session session = ServiceHibernate.currentSession();
            List results = session.createQuery(request).getResultList();
            regle = results.size() > 0 ? (RegleEntity) results.get(0) : null;
            session.close();
        } catch (Exception e) {
            throw new MonException("Récupération de l'action impossible: ", e.getMessage());
        }

        return regle;
    }

    public List<RegleEntity> getAllRegleById(String id) {
        List results;
        String request = "SELECT r FROM RegleEntity r WHERE r.numregle = " + id;
        try {
            Session session = ServiceHibernate.currentSession();
            results = session.createQuery(request).getResultList();
            session.close();
        } catch (Exception e) {
            throw new MonException("Récupération de l'action impossible: ", e.getMessage());
        }
        return results;
    }

    public void insert(RegleEntity regleEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.save(regleEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de l'action impossible : ", e.getMessage());
        }
    }

    public void delete(RegleEntity regleEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.delete(regleEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Suppression de l'action impossible : ", e.getMessage());
        }
    }

    public void update(RegleEntity regleEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.merge(regleEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Mise à jour de l'action impossible : ", e.getMessage());
        }
    }
}
