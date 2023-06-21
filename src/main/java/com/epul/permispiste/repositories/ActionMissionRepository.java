package com.epul.permispiste.repositories;

import com.epul.permispiste.domains.ActionJeuEntity;

import java.util.List;

public interface ActionMissionRepository {

    /*Récupère les missiosn liés à une action*/
    public List<ActionJeuEntity> findActionJeuEntitiesByFkAction(int id);

    /*Récupère les actions liés à une mission*/
    public List<ActionJeuEntity> findActionJeuEntitiesByFkJeu(int id);
}
