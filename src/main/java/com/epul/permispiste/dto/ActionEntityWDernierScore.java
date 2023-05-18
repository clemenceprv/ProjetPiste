package com.epul.permispiste.dto;

import com.epul.permispiste.domains.ActionEntity;
import org.hibernate.action.internal.EntityAction;

public class ActionEntityWDernierScore {

    private ActionEntity actionEntity;
    private String dernierScore;

    public ActionEntityWDernierScore(ActionEntity actionEntity, int score) {
        this.actionEntity = actionEntity;
        if (score == -1) {
            this.dernierScore = "Pas de score";
        } else {
            this.dernierScore = String.valueOf(score);
        }
    }

    public ActionEntity getActionEntity() {
        return actionEntity;
    }

    public void setActionEntity(ActionEntity actionEntity) {
        this.actionEntity = actionEntity;
    }

    public String getDernierScore() {
        return dernierScore;
    }

    public void setDernierScore(String dernierScore) {
        this.dernierScore = dernierScore;
    }
}
