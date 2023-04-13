package com.epul.oeuvre.repositories;


import com.epul.oeuvre.domains.ApprenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenantRepository extends JpaRepository<ApprenantEntity, Integer> {

}

