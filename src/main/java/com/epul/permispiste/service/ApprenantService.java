package com.epul.permispiste.service;

import com.epul.permispiste.repositories.ApprenantRepository;
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
