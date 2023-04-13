package com.epul.permispiste.domains;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "action__calendrier__apprenant", schema = "projetpermis1", catalog = "")
public class ActionCalendrierApprenantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fk_action", insertable = false, updatable = false)
    private int fkAction;
    @Basic
    @Column(name = "fk_apprenant")
    private int fkApprenant;
    @Basic
    @Column(name = "fk_calendrier")
    private Date fkCalendrier;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ActionEntity actionByFkAction;
    @ManyToOne
    @JoinColumn(name = "fk_apprenant", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ApprenantEntity apprenantByFkApprenant;
    @ManyToOne
    @JoinColumn(name = "fk_calendrier", referencedColumnName = "datejour", nullable = false, insertable = false, updatable = false)
    private CalendrierEntity calendrierByFkCalendrier;

    public int getFkAction() {
        return fkAction;
    }

    public void setFkAction(int fkAction) {
        this.fkAction = fkAction;
    }

    public int getFkApprenant() {
        return fkApprenant;
    }

    public void setFkApprenant(int fkApprenant) {
        this.fkApprenant = fkApprenant;
    }

    public Date getFkCalendrier() {
        return fkCalendrier;
    }

    public void setFkCalendrier(Date fkCalendrier) {
        this.fkCalendrier = fkCalendrier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionCalendrierApprenantEntity that = (ActionCalendrierApprenantEntity) o;

        if (fkAction != that.fkAction) return false;
        if (fkApprenant != that.fkApprenant) return false;
        if (fkCalendrier != null ? !fkCalendrier.equals(that.fkCalendrier) : that.fkCalendrier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkAction;
        result = 31 * result + fkApprenant;
        result = 31 * result + (fkCalendrier != null ? fkCalendrier.hashCode() : 0);
        return result;
    }

    public ActionEntity getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(ActionEntity actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }

    public ApprenantEntity getApprenantByFkApprenant() {
        return apprenantByFkApprenant;
    }

    public void setApprenantByFkApprenant(ApprenantEntity apprenantByFkApprenant) {
        this.apprenantByFkApprenant = apprenantByFkApprenant;
    }

    public CalendrierEntity getCalendrierByFkCalendrier() {
        return calendrierByFkCalendrier;
    }

    public void setCalendrierByFkCalendrier(CalendrierEntity calendrierByFkCalendrier) {
        this.calendrierByFkCalendrier = calendrierByFkCalendrier;
    }
}
