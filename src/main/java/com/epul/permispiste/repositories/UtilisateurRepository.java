package com.epul.permispiste.repositories;

import com.epul.permispiste.domains.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {

    public UtilisateurEntity getFirstByNomUtil(String nom);
    public UtilisateurEntity findByNumUtil(int numUtil);

    UtilisateurEntity findUtilisateurEntitiesByNumUtil(int idUser);

}

