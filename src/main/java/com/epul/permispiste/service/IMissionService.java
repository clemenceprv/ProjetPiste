package com.epul.permispiste.service;


import com.epul.permispiste.domains.ActionMissionEntity;
import com.epul.permispiste.domains.MissionEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IMissionService {

    MissionEntity addMission(MissionEntity missionEntity);

    MissionEntity getMissionById(int id);

    void editMission(MissionEntity missionEntity, Collection<ActionMissionEntity> actionMissionsById);

    void delete(int id);
}
