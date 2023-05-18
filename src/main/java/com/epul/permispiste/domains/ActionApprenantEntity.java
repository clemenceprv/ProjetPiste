package com.epul.permispiste.domains;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "action__apprenant", schema = "projetpermis1", catalog = "")
public class ActionApprenantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fk_action", insertable = false, updatable = false)
    private Integer fkAction;
    @Basic
    @Column(name = "fk_apprenant", insertable = false, updatable = false)
    private Integer fkApprenant;
    @Basic
    @Column(name = "fk_calendrier", insertable = false, updatable = false)
    private Date fkCalendrier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFkAction() {
        return fkAction;
    }

    public void setFkAction(Integer fkAction) {
        this.fkAction = fkAction;
    }

    public Integer getFkApprenant() {
        return fkApprenant;
    }

    public void setFkApprenant(Integer fkApprenant) {
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

        ActionApprenantEntity that = (ActionApprenantEntity) o;

        if (id != that.id) return false;
        if (fkAction != null ? !fkAction.equals(that.fkAction) : that.fkAction != null) return false;
        if (fkApprenant != null ? !fkApprenant.equals(that.fkApprenant) : that.fkApprenant != null) return false;
        if (fkCalendrier != null ? !fkCalendrier.equals(that.fkCalendrier) : that.fkCalendrier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fkAction != null ? fkAction.hashCode() : 0);
        result = 31 * result + (fkApprenant != null ? fkApprenant.hashCode() : 0);
        result = 31 * result + (fkCalendrier != null ? fkCalendrier.hashCode() : 0);
        return result;
    }
}
