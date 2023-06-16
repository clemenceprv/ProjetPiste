package com.epul.permispiste.repositories;

import com.epul.permispiste.domains.UtilisateurJeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurJeuRepository extends JpaRepository<UtilisateurJeuEntity, Integer> {

    public UtilisateurJeuEntity save(UtilisateurJeuEntity utilisateurJeuEntity);


}
