package fr.tourisme.react.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Restaurant extends Service {

    @Column
    private Integer capacite;

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite( Integer capacite ) {
        this.capacite = capacite;
    }

    @Column
    private String terrasse;

    public String getTerrasse() {
        return terrasse;
    }

    public void setTerrasse( String terrasse ) {
        this.terrasse = terrasse;
    }

    @Column
    private String reservation;

    public String getReservation() {
        return reservation;
    }

    public void setReservation( String reservation ) {
        this.reservation = reservation;
    }

    @Column( name = "marge_Fixe" )
    private Integer margeFixe;

    public Integer getMargeFixe() {
        return margeFixe;
    }

    public void setMargeFixe( Integer margeFixe ) {
        this.margeFixe = margeFixe;
    }

}
