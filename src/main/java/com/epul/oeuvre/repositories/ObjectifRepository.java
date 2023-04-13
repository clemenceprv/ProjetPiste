package com.epul.oeuvre.repositories;


import com.epul.oeuvre.domains.RegleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectifRepository
        extends JpaRepository<RegleEntity, Integer> {
}