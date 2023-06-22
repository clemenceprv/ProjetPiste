package com.epul.permispiste.repositories;

import com.epul.permispiste.domains.ActionJeuEntity;

import java.util.List;

public interface ActionMissionRepository extends JpaRepository<ActionMissionEntity, Integer> {


    /*Récupère les actions liés à une mission*/
    List<ActionMissionEntity> findActionMissionEntitiesByFkMission(int idFkMission);


}
