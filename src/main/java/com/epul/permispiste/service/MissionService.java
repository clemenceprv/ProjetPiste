package com.epul.permispiste.service;

import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.mesExceptions.MonException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MissionService implements IMissionService {

    public List<MissionEntity> getAll() throws MonException {
        List<MissionEntity> missions;
        String request = "SELECT m FROM MissionEntity m";

        try {
            Session session = ServiceHibernate.currentSession();
            missions = session.createQuery(request, MissionEntity.class).getResultList();
            session.close();
        } catch (Exception e) {
            throw new MonException("Impossible d'accéder à la SessionFactory: ", e.getMessage());
        }

        return missions;
    }

    public MissionEntity getMissionById(int id) {
        MissionEntity missions;
        String request = "SELECT m FROM MissionEntity m WHERE m.id=" + id;
        try {
            Session session = ServiceHibernate.currentSession();
            missions = (MissionEntity) session.createQuery(request).getResultList().get(0);
            session.close();
        } catch (Exception e) {
            throw new MonException("Récupération de la mission impossible: ", e.getMessage());
        }

        return missions;
    }

    public MissionEntity getMissionWithObjectifsById(int id) {
        MissionEntity missions;
        String request = "SELECT m FROM MissionEntity m WHERE m.nummission=" + id;
        try {
            Session session = ServiceHibernate.currentSession();
            List results = session.createQuery(request).getResultList();
            missions = results.size() > 0 ? (MissionEntity) results.get(0) : null;
            session.close();
        } catch (Exception e) {
            throw new MonException("Récupération de la mission impossible: ", e.getMessage());
        }

        return missions;
    }

    public void insert(MissionEntity missionEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.save(missionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de la mission impossible : ", e.getMessage());
        }
    }

    public void delete(MissionEntity missionEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.delete(missionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de la mission impossible : ", e.getMessage());
        }
    }

    public void update(MissionEntity missionEntity) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.merge(missionEntity);
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de la mission impossible : ", e.getMessage());
        }
    }
}
