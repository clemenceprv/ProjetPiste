package com.epul.permispiste.domains;

import javax.persistence.*;

@Entity
@Table(name = "inscription__action", schema = "projetpermis1", catalog = "")
public class InscriptionActionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fk_inscription")
    private int fkInscription;
    @Basic
    @Column(name = "fk_action")
    private int fkAction;
    @Basic
    @Column(name = "sort")
    private Integer sort;
    @Basic
    @Column(name = "score")
    private Integer score;
    @ManyToOne
    @JoinColumn(name = "fk_inscription", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private InscriptionEntity inscriptionByFkInscription;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ActionEntity actionByFkAction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkInscription() {
        return fkInscription;
    }

    public void setFkInscription(int fkInscription) {
        this.fkInscription = fkInscription;
    }

    public int getFkAction() {
        return fkAction;
    }

    public void setFkAction(int fkAction) {
        this.fkAction = fkAction;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscriptionActionEntity that = (InscriptionActionEntity) o;

        if (id != that.id) return false;
        if (fkInscription != that.fkInscription) return false;
        if (fkAction != that.fkAction) return false;
        if (sort != null ? !sort.equals(that.sort) : that.sort != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fkInscription;
        result = 31 * result + fkAction;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }

    public InscriptionEntity getInscriptionByFkInscription() {
        return inscriptionByFkInscription;
    }

    public void setInscriptionByFkInscription(InscriptionEntity inscriptionByFkInscription) {
        this.inscriptionByFkInscription = inscriptionByFkInscription;
    }

    public ActionEntity getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(ActionEntity actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }
}
