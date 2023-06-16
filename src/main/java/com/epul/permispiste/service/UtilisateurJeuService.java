package com.epul.permispiste.service;

import com.epul.permispiste.repositories.UtilisateurJeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurJeuService {

    public UtilisateurJeuRepository utilisateurJeuRepository;

    @Autowired
    public UtilisateurJeuService(UtilisateurJeuRepository utilisateurJeuRepository) {
        this.utilisateurJeuRepository = utilisateurJeuRepository;
    }
}
