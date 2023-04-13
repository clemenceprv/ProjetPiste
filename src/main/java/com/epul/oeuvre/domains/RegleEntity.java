package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "regle", schema = "projetpermis1", catalog = "")
public class RegleEntity {
    private int id;
    private int scoreMin;
    private String libRegle;

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "scoreMin")
    public int getScoreMin() {
        return scoreMin;
    }

    public void setScoreMin(int scoreMin) {
        this.scoreMin = scoreMin;
    }

    @Basic
    @Column(name = "libRegle")
    public String getLibRegle() {
        return libRegle;
    }

    public void setLibRegle(String libRegle) { this.libRegle = libRegle;}


    @Override
    public int hashCode() {
        return Objects.hash(id, scoreMin, libRegle);
    }


}
