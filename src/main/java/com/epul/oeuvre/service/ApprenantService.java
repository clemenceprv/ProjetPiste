package com.epul.oeuvre.service;

import com.epul.oeuvre.domains.IndicateurEntity;
import com.epul.oeuvre.mesExceptions.MonException;
import com.epul.oeuvre.repositories.ApprenantRepository;
import com.epul.oeuvre.utilitaires.MonMotPassHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprenantService implements IApprenantService {

    private ApprenantRepository unApprenantRepostory;

    @Autowired
    public ApprenantService(ApprenantRepository ApprenantRepostory) {
        this.unApprenantRepostory = ApprenantRepostory;
    }

}
