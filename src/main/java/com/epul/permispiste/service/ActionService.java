package com.epul.permispiste.service;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class ActionService{

    @Autowired
    private ActionRepository actionRepository;

    public ActionEntity getAction(int id) {
        ActionEntity action = null;
        try {
            action = actionRepository.findById(id).orElse(action);
        } catch (Exception e) {
            System.out.println(e);
        }
        return action;
    }


    public ActionEntity getActionById(int id) {
        return actionRepository.findActionEntityById(id);

    }

    public List<ActionEntity> getAllAction() {
        List<ActionEntity> actions=null;
        try {
            actions= actionRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return actions;
    }

    public void addAction(ActionEntity actionEntity) {
        actionRepository.save(actionEntity);
    }

    public void editAction(ActionEntity actionEntity) {
        ActionEntity action = actionRepository.findById(actionEntity.getId()).get();
        action.setWording(actionEntity.getWording());
        action.setScoreMinimum(actionEntity.getScoreMinimum());
        actionRepository.save(action);
    }

    public void delete(int id) {
        ActionEntity action = actionRepository.findById(id).get();
        actionRepository.delete(action);
    }

}
