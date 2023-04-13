package com.epul.permispiste.repositories;


import com.epul.permispiste.domains.RegleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectifRepository
        extends JpaRepository<RegleEntity, Integer> {
}