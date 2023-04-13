package com.epul.oeuvre.repositories;

import com.epul.oeuvre.domains.JeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<JeuEntity, Integer> {



}


