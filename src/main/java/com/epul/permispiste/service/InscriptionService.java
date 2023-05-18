package com.epul.permispiste.service;

import com.epul.permispiste.domains.InscriptionEntity;
import com.epul.permispiste.repositories.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InscriptionService implements IInscriptionService {

    public InscriptionRepository inscriptionRepository;

    @Autowired
    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public List<InscriptionEntity> getInscriptionsByIdUsers(int id) {
        return inscriptionRepository.findAllByFkUser(id);
    }

   

}
