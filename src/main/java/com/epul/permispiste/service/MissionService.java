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

    //  ***************************************
    //  On récupère la liste des missions
    //  ***************************************
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

    //  ***************************************
    //  Obtention d'une mission par son id
    //  ***************************************
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

    //  ***************************************
    //  Ajout d'une mission
    //  ***************************************
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

    //  ***************************************
    //  Suppression d'une mission
    //  ***************************************
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

    //  ***************************************
    //  Mise à jours d'une mission
    //  ***************************************
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

    //  ***************************************
    //  Ajouter une action à une mission
    //  ***************************************
    public void addActionToMission(int idMission, int idAction) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.createNativeQuery("INSERT INTO mission_action (idmission, idaction) VALUES (" + idMission + ", " + idAction + ")").executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Insertion de l'action impossible : ", e.getMessage());
        }
    }

    //  ***************************************
    //  Supprimer une action d'une mission
    //  ***************************************
    public void removeActionFromMission(int idMission, int idAction) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.createNativeQuery("DELETE FROM mission_action WHERE idmission=" + idMission + " AND idaction=" + idAction).executeUpdate();
            tx.commit();
            session.close();
        } catch (Exception e) {
            throw new MonException("Suppression de l'action impossible : ", e.getMessage());
        }
    }
}
