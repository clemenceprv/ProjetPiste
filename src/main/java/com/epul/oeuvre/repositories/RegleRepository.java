package com.epul.oeuvre.repositories;


import com.epul.oeuvre.domains.InscritEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegleRepository
        extends JpaRepository<InscritEntity, Integer> {

}

