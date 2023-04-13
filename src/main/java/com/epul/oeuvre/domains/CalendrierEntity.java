package com.epul.oeuvre.domains;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "adherent", schema = "projetpermis1", catalog = "")
public class CalendrierEntity {
    private Date dateJour;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id_adherent")
    public Date getDateJour() {
        return dateJour;
    }

    public void setDateJour(Date dateJour) {
        this.dateJour = dateJour;
    }


    @Override
    public int hashCode() {
        return Objects.hash(dateJour);
    }
}
