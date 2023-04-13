package com.epul.permispiste.domains;

import javax.persistence.*;

@Entity
@Table(name = "action__objectif", schema = "projetpermis1", catalog = "")
public class ActionObjectifEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fk_action")
    private int fkAction;
    @Basic
    @Column(name = "fk_objectif")
    private int fkObjectif;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ActionEntity actionByFkAction;
    @ManyToOne
    @JoinColumn(name = "fk_objectif", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ObjectifEntity objectifByFkObjectif;

    public int getFkAction() {
        return fkAction;
    }

    public void setFkAction(int fkAction) {
        this.fkAction = fkAction;
    }

    public int getFkObjectif() {
        return fkObjectif;
    }

    public void setFkObjectif(int fkObjectif) {
        this.fkObjectif = fkObjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionObjectifEntity that = (ActionObjectifEntity) o;

        if (fkAction != that.fkAction) return false;
        if (fkObjectif != that.fkObjectif) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkAction;
        result = 31 * result + fkObjectif;
        return result;
    }

    public ActionEntity getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(ActionEntity actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }

    public ObjectifEntity getObjectifByFkObjectif() {
        return objectifByFkObjectif;
    }

    public void setObjectifByFkObjectif(ObjectifEntity objectifByFkObjectif) {
        this.objectifByFkObjectif = objectifByFkObjectif;
    }
}
