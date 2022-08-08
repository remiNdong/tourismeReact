package fr.tourisme.react.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn( name = "id" )
public class Hotel extends Service {

    @Column
    private Integer classement;

    public Integer getClassement() {
        return classement;
    }

    public void setClassement( Integer classement ) {
        this.classement = classement;
    }

    @Column( name = "nb_Chambres" )
    private Integer nbChambres;

    public Integer getNbChambres() {
        return nbChambres;
    }

    public void setNbChambres( Integer nbChambres ) {
        this.nbChambres = nbChambres;
    }

    @Column( name = "pourcentage_Marge" )
    private Integer pourcentageMarge;

    public Integer getPourcentageMarge() {
        return pourcentageMarge;
    }

    public void setPourcentageMarge( Integer pourcentageMarge ) {
        this.pourcentageMarge = pourcentageMarge;
    }

}

