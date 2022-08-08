package fr.tourisme.react.service;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//Adresse est mappé  grace aux colonnes adresse, ville
//et pays de la Table Service. Adresse sera un attribut d'un Service
@Embeddable
public class Adresse {

 @Column
 private String adresse;

 public void setAdresse( String v ) {
     adresse = v;
 }

 public String getAdresse() {
     return adresse;
 }

 @Column
 private String ville;

 public String getVille() {
     return ville;
 }

 public void setVille( String ville ) {
     this.ville = ville;
 }

 @Column
 private String pays;

 public String getPays() {
     return pays;
 }

 public void setPays( String pays ) {
     this.pays = pays;
 }

 public String toString() {

     return adresse + " " + ville + " -  " + pays;
 }
}

