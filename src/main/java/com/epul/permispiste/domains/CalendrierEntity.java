package com.epul.permispiste.domains;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "calendrier", schema = "projetpermis1", catalog = "")
public class CalendrierEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "datejour")
    private Date datejour;
    @OneToMany(mappedBy = "calendrierByFkCalendrier")
    private Collection<ActionCalendrierApprenantEntity> actionCalendrierApprenantsByDatejour;
    @OneToMany(mappedBy = "calendrierByFkCalendrier")
    private Collection<ApprenantJeuCalendrierEntity> apprenantJeuCalendriersByDatejour;

    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendrierEntity that = (CalendrierEntity) o;

        if (datejour != null ? !datejour.equals(that.datejour) : that.datejour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return datejour != null ? datejour.hashCode() : 0;
    }

    public Collection<ActionCalendrierApprenantEntity> getActionCalendrierApprenantsByDatejour() {
        return actionCalendrierApprenantsByDatejour;
    }

    public void setActionCalendrierApprenantsByDatejour(Collection<ActionCalendrierApprenantEntity> actionCalendrierApprenantsByDatejour) {
        this.actionCalendrierApprenantsByDatejour = actionCalendrierApprenantsByDatejour;
    }

    public Collection<ApprenantJeuCalendrierEntity> getApprenantJeuCalendriersByDatejour() {
        return apprenantJeuCalendriersByDatejour;
    }

    public void setApprenantJeuCalendriersByDatejour(Collection<ApprenantJeuCalendrierEntity> apprenantJeuCalendriersByDatejour) {
        this.apprenantJeuCalendriersByDatejour = apprenantJeuCalendriersByDatejour;
    }
}
