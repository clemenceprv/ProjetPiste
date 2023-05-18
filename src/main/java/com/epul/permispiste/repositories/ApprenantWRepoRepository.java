package com.epul.permispiste.repositories;


import com.epul.permispiste.domains.ApprenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenantWRepoRepository extends JpaRepository<ApprenantEntity, Integer> {

    public ApprenantEntity findById(int id);

}

