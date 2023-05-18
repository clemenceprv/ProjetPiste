package com.epul.permispiste.service;

import com.epul.permispiste.domains.ApprenantEntity;
import com.epul.permispiste.domains.InscriptionEntity;
import com.epul.permispiste.mesExceptions.MonException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ApprenantService implements IApprenantService {

    //  ***************************************
    //  On récupère la liste des apprenants
    //  ***************************************
    public List<ApprenantEntity> consulterListeApprenants() throws MonException {
        List<ApprenantEntity> apprenants;
        String request = "SELECT l FROM ApprenantEntity l";
        try {
            Session session = ServiceHibernate.currentSession();
            apprenants = session.createQuery(request, ApprenantEntity.class).getResultList();
            session.close();
        } catch (HibernateException ex) {
            throw ex;
        }
        return apprenants;
    }

    //  ***************************************
    //  On récupère la liste des apprenants rechercher
    //  ***************************************
    public List<ApprenantEntity> searchListeApprenants(String s) throws MonException {
        List<ApprenantEntity> apprenants;
        String request = "SELECT l FROM ApprenantEntity l WHERE l.nomApprenant LIKE '%"+s+"%' OR l.prenomApprenant LIKE '%"+s+"%'";
        try {
            Session session = ServiceHibernate.currentSession();
            apprenants = session.createQuery(request, ApprenantEntity.class).getResultList();
            session.close();
        } catch (HibernateException ex) {
            throw ex;
        }
        return apprenants;
    }

    //  ***************************************
    //  On ajoute un apprenant à la base
    //  ***************************************
    public void insertApprenant(ApprenantEntity unAdh) throws MonException {
        Transaction tx = null;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.save(unAdh);
            tx.commit();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (tx != null)
                tx.rollback();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    //  ***************************************
    //  On récupère un apprenant de base
    //  ***************************************
    public ApprenantEntity adherentById(int numero) throws MonException {
        List<ApprenantEntity> apprenants;
        String request = "SELECT l FROM ApprenantEntity l WHERE l.id=" + numero;
        try {
            Session session = ServiceHibernate.currentSession();
            apprenants = session.createQuery(request, ApprenantEntity.class).getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return apprenants.get(0);
    }

    //  ***************************************
    //  On supprime un apprenant à la base
    //  ***************************************
    public void deleteApprenant(ApprenantEntity apprenant) throws MonException {
        Transaction tx = null;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.delete(apprenant);
            tx.commit();
            session.close();
        } catch (MonException e){
            e.printStackTrace();
            throw e;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //  ***************************************
    //  Mise à jours d'un apprenant
    //  ***************************************
    public void update(ApprenantEntity apprenant) {
        Transaction tx;
        try {
            Session session = ServiceHibernate.currentSession();
            tx = session.beginTransaction();
            session.merge(apprenant);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Collection<InscriptionEntity> getInscritsByNumapprenant(int id){
        List<InscriptionEntity> inscriptions;
        String request = "SELECT l FROM InscriptionEntity l WHERE l.fkUser=" + id;
        try {
            Session session = ServiceHibernate.currentSession();
            inscriptions = session.createQuery(request, InscriptionEntity.class).getResultList();
            session.close();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return inscriptions;
    }


}
