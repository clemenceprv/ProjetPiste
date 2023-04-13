package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "objectif", schema = "projetpermis1", catalog = "")
public class ObjectifEntity {
    private Integer idObjectif;
    private String libObectif;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getIdObjectif() {
        return idObjectif;
    }

    public void setIdObjectif(int idObjectif) {
        this.idObjectif = idObjectif;
    }

    @Basic
    @Column(name = "libObectif")
    public String getLibObjectif() {
        return libObectif;
    }

    public void setLibObjectif(String libObectif) {
        this.libObectif = libObectif;
    }


    @Override
    public int hashCode() {
        return Objects.hash(idObjectif, libObectif);
    }
}
