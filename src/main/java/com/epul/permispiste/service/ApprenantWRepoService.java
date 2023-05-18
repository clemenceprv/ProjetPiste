package com.epul.permispiste.service;

import com.epul.permispiste.domains.ApprenantEntity;
import com.epul.permispiste.repositories.ApprenantWRepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprenantWRepoService{

    private ApprenantWRepoRepository apprenantWRepoRepository;

    @Autowired
    public ApprenantWRepoService(ApprenantWRepoRepository apprenantWRepoRepository) {
        this.apprenantWRepoRepository = apprenantWRepoRepository;
    }

    public ApprenantEntity getUnApprenantID(int id) {
        return apprenantWRepoRepository.findById(id);
    }

}
