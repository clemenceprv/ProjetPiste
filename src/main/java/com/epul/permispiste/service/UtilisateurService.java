package com.epul.permispiste.service;

import com.epul.permispiste.domains.UtilisateurEntity;
import com.epul.permispiste.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService
{
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository)
    {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurEntity getUtilisateurById(int id)
    {
        return utilisateurRepository.findByNumUtil(id);
    }


    public UtilisateurEntity getUserByFkKey(int idUser) {
        return utilisateurRepository.findUtilisateurEntitiesByNumUtil(idUser);
    }
}
