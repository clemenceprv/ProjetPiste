package com.epul.permispiste.domains;

import javax.persistence.*;

@Entity
@Table(name = "action__regle", schema = "projetpermis1", catalog = "")
public class ActionRegleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fk_action")
    private int fkAction;
    @Basic
    @Column(name = "fk_regle")
    private int fkRegle;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ActionEntity actionByFkAction;
    @ManyToOne
    @JoinColumn(name = "fk_regle", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private RegleEntity regleByFkRegle;

    public int getFkAction() {
        return fkAction;
    }

    public void setFkAction(int fkAction) {
        this.fkAction = fkAction;
    }

    public int getFkRegle() {
        return fkRegle;
    }

    public void setFkRegle(int fkRegle) {
        this.fkRegle = fkRegle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionRegleEntity that = (ActionRegleEntity) o;

        if (fkAction != that.fkAction) return false;
        if (fkRegle != that.fkRegle) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkAction;
        result = 31 * result + fkRegle;
        return result;
    }

    public ActionEntity getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(ActionEntity actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }

    public RegleEntity getRegleByFkRegle() {
        return regleByFkRegle;
    }

    public void setRegleByFkRegle(RegleEntity regleByFkRegle) {
        this.regleByFkRegle = regleByFkRegle;
    }
}
