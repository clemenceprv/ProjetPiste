package com.epul.permispiste.service;

import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.repositories.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService implements IMissionService {
    private MissionRepository missionRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository) {
       this.missionRepository = missionRepository;
    }
    public List<MissionEntity> findAll() { return missionRepository.findAll();}
}
