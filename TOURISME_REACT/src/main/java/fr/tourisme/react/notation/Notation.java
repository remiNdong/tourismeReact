package fr.tourisme.react.notation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fr.tourisme.react.internaute.Internaute;
import fr.tourisme.react.service.Service;

@Entity
public class Notation implements Comparable<Notation> {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    // le choix a ete fait de ne creer qu'une seule classe de notation
    // reunissant les criteres de tous les types de Service avec des valeurs par
    // defaut. Pour un type de Service, seuls les criteres le concernant seront
    // affichés par l'application
    @Column( name = "nb_Etoiles" )
    private Integer nbEtoiles;

    public Integer getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles( Integer nbEtoiles ) {
        this.nbEtoiles = nbEtoiles;
    }

    @Column( name = "proprete_Hotel" )
    private Integer propreteHotel;

    public Integer getPropreteHotel() {
        return propreteHotel;
    }

    public void setPropreteHotel( Integer propreteHotel ) {
        this.propreteHotel = propreteHotel;
    }

    @Column( name = "calme_Hotel" )
    private Integer calmeHotel;

    public Integer getCalmeHotel() {
        return calmeHotel;
    }

    public void setCalmeHotel( Integer calmeHotel ) {
        this.calmeHotel = calmeHotel;
    }

    @Column( name = "acceuil_Restaurant" )
    private Integer acceuilRestaurant;

    public Integer getAcceuilRestaurant() {
        return acceuilRestaurant;
    }

    public void setAcceuilRestaurant( Integer acceuilRestaurant ) {
        this.acceuilRestaurant = acceuilRestaurant;
    }

    @Column( name = "qualite_Restaurant" )
    private Integer qualiteRestaurant;

    public Integer getQualiteRestaurant() {
        return qualiteRestaurant;
    }

    public void setQualiteRestaurant( Integer qualiteRestaurant ) {
        this.qualiteRestaurant = qualiteRestaurant;
    }

    @Column( name = "sensations_Activite" )
    private Integer sensationsActivite;

    public Integer getSensationsActivite() {
        return sensationsActivite;
    }

    public void setSensationsActivite( Integer sensationsActivite ) {
        this.sensationsActivite = sensationsActivite;
    }

    @Column( name = "qualite_Accompagnateur" )
    private Integer qualiteAccompagnateur;

    public Integer getQualiteAccompagnateur() {
        return qualiteAccompagnateur;
    }

    public void setQualiteAccompagnateur( Integer qualiteAccompagnateur ) {
        this.qualiteAccompagnateur = qualiteAccompagnateur;
    }

    @Column
    private String commentaire;

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire( String commentaire ) {
        this.commentaire = commentaire;
    }

    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn( name = "id_Internaute" )
    @JsonBackReference // annotation pour eviter les infinite recursion
    private Internaute internaute;

    public Internaute getInternaute() {
        return internaute;
    }

    public void setInternaute( Internaute internaute ) {
        this.internaute = internaute;
    }

    @ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn( name = "id_Service" )
    @JsonBackReference // annotation pour eviter les infinite recursion
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService( Service service ) {
        this.service = service;
    }

    @Override
    public int hashCode() {

        return id.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( !( obj instanceof Notation ) ) {
            return false;
        }
        Notation autre = (Notation) obj;

        return this.getId() == autre.getId();
    }

    @Override
    public int compareTo( Notation n ) {
        return this.id.compareTo( n.getId() );
    }

}
