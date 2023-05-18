package com.epul.permispiste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApprenantJeuService {


    /*private ApprenantJeuRepository apprenantJeuRepository;
    private ActionWRepoService ActionWRepoService;

    @Autowired
    public ApprenantJeuService(ApprenantJeuRepository apprenantJeuRepository, ActionWRepoService ActionWRepoService) {
        this.apprenantJeuRepository = apprenantJeuRepository;
        this.ActionWRepoService = ActionWRepoService;
    }

    //TODO : Adapter la méthode pour qu'elle prenne en compte les actions possibles
    public List<ActionEntity> getActionsPossiblesParApprenants(ApprenantEntity apprenantEntity) {
        System.out.println("ALAID1");
        List<ApprenantJeuEntity> listeApprenantsJeuCalendrier =  apprenantJeuRepository.findAllByFkApprenant(apprenantEntity.getId());
        System.out.println("ALAID2");
        System.out.println(listeApprenantsJeuCalendrier);
        List<ActionEntity> listeActionsPossibles = new ArrayList<>();

        for (ApprenantJeuEntity unJeu : listeApprenantsJeuCalendrier) {

            ActionEntity actionEntity = ActionWRepoService.getActionById(unJeu.getFkAction());
            if(actionEntity.getFkAction() == null)
                listeActionsPossibles.add(actionEntity);
            else
            {
                if(actionEntity.getScoreMinimum() <= unJeu.getValeurdebut())
                    listeActionsPossibles.add(actionEntity);
            }
        }

        return listeActionsPossibles;
    }*/
}
