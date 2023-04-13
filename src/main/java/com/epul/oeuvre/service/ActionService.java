package com.epul.oeuvre.service;

import com.epul.oeuvre.domains.ActionEntity;
import com.epul.oeuvre.mesExceptions.MonException;
import com.epul.oeuvre.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService implements IActionService {

    @Autowired
    private ActionRepository uneActionRepository;

    @Override
    public List<ActionEntity> getToutesLesActions() {
            return null;
        }


    public ActionEntity getUneActionID(int id) {
        return null;
    }

    // on insère une action
    @Override
    public void ajouterAction(ActionEntity unA) {
        try {

        } catch (Exception e) {
            throw new MonException("Insert", "Sql", e.getMessage());
        }
    }
}
