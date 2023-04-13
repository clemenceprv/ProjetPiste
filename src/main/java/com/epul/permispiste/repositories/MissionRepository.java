package com.epul.permispiste.repositories;

import com.epul.permispiste.domains.JeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<JeuEntity, Integer> {



}


