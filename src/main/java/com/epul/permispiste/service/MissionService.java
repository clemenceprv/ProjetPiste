package com.epul.permispiste.service;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.domains.ActionMissionEntity;
import com.epul.permispiste.domains.MissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epul.permispiste.repositories.MissionRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class MissionService implements IMissionService {

    @Autowired
    private MissionRepository missionRepository;

    public List<MissionEntity> getAll(){
        List<MissionEntity> missions=null;
        try {
            missions = missionRepository.findAll();
        }catch (Exception e) {
            System.out.println(e);
        }
        return missions;
    }

    public void editMission(MissionEntity missionEntity, Collection<ActionMissionEntity> actionMissions) {
        MissionEntity mission = missionRepository.findById(missionEntity.getId());
        mission.setActionMissionsById(actionMissions);
        mission.setWording(missionEntity.getWording());
        missionRepository.save(mission);
    }

    @Override
    public MissionEntity addMission(MissionEntity missionEntity)  {
        return missionRepository.save(missionEntity);
    }

    @Override
    public MissionEntity getMissionById(int id) {
        return missionRepository.findById(id);
    }

    public void delete(int id) {
        MissionEntity mission = missionRepository.findById(id);
        missionRepository.delete(mission);
    }
}
