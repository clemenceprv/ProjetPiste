package com.epul.permispiste.repositories;

import com.epul.permispiste.domains.ActionMissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionMissionRepository extends JpaRepository<ActionMissionEntity, Integer> {
}
