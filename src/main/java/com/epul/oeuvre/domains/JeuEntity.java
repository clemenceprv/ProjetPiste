package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jeu", schema = "projetpermis1", catalog = "")
public class JeuEntity {
    private int id;
    private String libJeu;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "libelleJeu")
    public String getLibJeu() {
        return libJeu;
    }

    public void setLibJeu(String libJeu) {
        this.libJeu = libJeu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JeuEntity that = (JeuEntity) o;
        return id == that.id && Objects.equals(libJeu, that.libJeu) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libJeu);
    }
}
