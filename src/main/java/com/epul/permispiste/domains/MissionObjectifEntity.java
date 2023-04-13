package com.epul.permispiste.domains;

import javax.persistence.*;

@Entity
@Table(name = "mission__objectif", schema = "projetpermis1", catalog = "")
public class MissionObjectifEntity {
    @Basic
    @Column(name = "fk_mission")
    private int fkMission;
    @Basic
    @Column(name = "fk_objectif")
    private int fkObjectif;
    @ManyToOne
    @JoinColumn(name = "fk_mission", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private MissionEntity missionByFkMission;
    @ManyToOne
    @JoinColumn(name = "fk_objectif", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ObjectifEntity objectifByFkObjectif;
    @Id
    private Long id;

    public int getFkMission() {
        return fkMission;
    }

    public void setFkMission(int fkMission) {
        this.fkMission = fkMission;
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

        MissionObjectifEntity that = (MissionObjectifEntity) o;

        if (fkMission != that.fkMission) return false;
        if (fkObjectif != that.fkObjectif) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkMission;
        result = 31 * result + fkObjectif;
        return result;
    }

    public MissionEntity getMissionByFkMission() {
        return missionByFkMission;
    }

    public void setMissionByFkMission(MissionEntity missionByFkMission) {
        this.missionByFkMission = missionByFkMission;
    }

    public ObjectifEntity getObjectifByFkObjectif() {
        return objectifByFkObjectif;
    }

    public void setObjectifByFkObjectif(ObjectifEntity objectifByFkObjectif) {
        this.objectifByFkObjectif = objectifByFkObjectif;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
