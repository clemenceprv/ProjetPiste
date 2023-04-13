package com.epul.permispiste.domains;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "objectif", schema = "projetpermis1", catalog = "")
public class ObjectifEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "libObjectif")
    private String libObjectif;
    @OneToMany(mappedBy = "objectifByFkObjectif")
    private Collection<ActionObjectifEntity> actionObjectifsById;
    @OneToMany(mappedBy = "objectifByFkObjectif")
    private Collection<MissionObjectifEntity> missionObjectifsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibObjectif() {
        return libObjectif;
    }

    public void setLibObjectif(String libObjectif) {
        this.libObjectif = libObjectif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectifEntity that = (ObjectifEntity) o;

        if (id != that.id) return false;
        if (libObjectif != null ? !libObjectif.equals(that.libObjectif) : that.libObjectif != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (libObjectif != null ? libObjectif.hashCode() : 0);
        return result;
    }

    public Collection<ActionObjectifEntity> getActionObjectifsById() {
        return actionObjectifsById;
    }

    public void setActionObjectifsById(Collection<ActionObjectifEntity> actionObjectifsById) {
        this.actionObjectifsById = actionObjectifsById;
    }

    public Collection<MissionObjectifEntity> getMissionObjectifsById() {
        return missionObjectifsById;
    }

    public void setMissionObjectifsById(Collection<MissionObjectifEntity> missionObjectifsById) {
        this.missionObjectifsById = missionObjectifsById;
    }
}
