package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "action", schema = "projetpermis1", catalog = "")
public class ActionEntity {
    private Integer id;
    private Integer scoreMin;
    private String libaction;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "scoreMininimum")
    public Integer getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(int scoreMin) {
        this.scoreMin = scoreMin;
    }

    @Basic
    @Column(name = "wording")
    public String getLibaction() {
        return libaction;
    }

    public void setLibaction(String libaction) {
        this.libaction = libaction;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, scoreMin, libaction);
    }
}
