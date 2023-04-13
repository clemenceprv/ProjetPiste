package com.epul.oeuvre.repositories;


import com.epul.oeuvre.domains.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<ActionEntity, Integer> {

}

