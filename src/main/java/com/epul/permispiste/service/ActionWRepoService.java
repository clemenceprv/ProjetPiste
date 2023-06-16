package com.epul.permispiste.service;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionWRepoService {

    private ActionRepository actionRepository;

    @Autowired
    public ActionWRepoService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public ActionEntity getActionById(int id) {
        return actionRepository.findActionEntityById(id);
    }

}
