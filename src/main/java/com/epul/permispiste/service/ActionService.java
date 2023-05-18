package com.epul.permispiste.service;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService{

    @Autowired
    private ActionRepository uneActionRepository;

    public ActionEntity getUneActionID(int id) {
        return null;
    }

}
