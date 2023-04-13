package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "emprunt", schema = "baseoeuvre", catalog = "")
public class ApprenantEntity {
    private int id;
    private String nomApprenant;
    private String prenomApprenant;

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    public int getId() {
        return id;
    }

    public void setId(int idEmprunt) {
        this.id = id;
    }

    @Basic
    @Column(name = "nomApprenant", insertable = false, updatable = false)
    public String getNomApprenant() {
        return nomApprenant;
    }

    public void setNomApprenant(String idAdherent) {
        this.nomApprenant = nomApprenant;
    }

    @Basic
    @Column(name = "prenomApprenant", insertable = false, updatable = false)
    public String getPrenomApprenant() {
        return prenomApprenant;
    }

    public void setPrenomApprenant(String idOeuvrepret) {
        this.prenomApprenant = prenomApprenant;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, nomApprenant, prenomApprenant);
    }
}
