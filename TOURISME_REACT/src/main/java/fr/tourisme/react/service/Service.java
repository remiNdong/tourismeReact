package fr.tourisme.react.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import fr.tourisme.react.notation.Notation;
import fr.tourisme.react.offre.Offre;

@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Service {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Column
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    @Column
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    // Adresse est mappé par la classe Adresse grace aux colonnes adresse, ville
    // et pays de Service
    @Embedded
    private Adresse adresse;

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse( Adresse adresse ) {
        this.adresse = adresse;
    }

    // le mapping est fait par l'attribut prestataire de Offre
    @OneToMany( mappedBy = "prestataire" )
    private Set<Offre> offresProposees = new HashSet<Offre>();

    public Set<Offre> getOffresProposees() {
        return offresProposees;
    }

    public void addOffresProposees( Offre o ) {

        o.setPrestataire( this );
        offresProposees.add( o );
    }

    @OneToOne( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn( name = "id_offre" )
    Offre meilleureOffre;

    public Offre getMeilleureOffre() {
        return meilleureOffre;
    }

    public void setMeilleureOffre( Offre meilleureOffre ) {
        this.meilleureOffre = meilleureOffre;
    }

    @OneToMany( mappedBy = "service" )
    private Set<Notation> notations = new HashSet<Notation>();

    public Set<Notation> getNotations() {
        return notations;
    }

    public List<Notation> getNotationsTriees() {
        List<Notation> listNotations = new ArrayList<Notation>( notations );
        Collections.sort( listNotations );
        return listNotations;
    }

    public void addNotations( Notation n ) {

        n.setService( this );
        notations.add( n );
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
        if ( !( obj instanceof Service ) ) {
            return false;
        }
        Service autre = (Service) obj;

        return this.getId() == autre.getId();
    }

}

