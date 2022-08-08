package fr.tourisme.react.internaute;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.tourisme.react.notation.Notation;

@Entity
public class Internaute {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    @Column
    private String nom;

    public void setNom( String n ) {
        nom = n;
    }

    public String getNom() {
        return nom;
    }

    @Column
    private String prenom;

    public void setPrenom( String p ) {
        prenom = p;
    }

    public String getPrenom() {
        return prenom;
    }

    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail( String e ) {
        email = e;
    }

    @OneToMany( mappedBy = "internaute" )
    private Set<Notation> notations = new HashSet<Notation>();

    public Set<Notation> getNotations() {
        return notations;
    }

    public void addNotations( Notation n ) {

        n.setInternaute( this );
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
        if ( !( obj instanceof Internaute ) ) {
            return false;
        }
        Internaute autre = (Internaute) obj;

        return this.getId() == autre.getId();
    }

}
