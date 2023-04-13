package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mission", schema = "projetpermis1", catalog = "")
public class MissionEntity {
    private int id;
    private String libMission;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "wording")
    public String getLibMission() {
        return libMission;
    }

    public void setLibMission(String libMission) {
        this.libMission = libMission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libMission);
    }
}
