package fr.tourisme.react.offre;

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

import fr.tourisme.react.activite.Activite;
import fr.tourisme.react.hotel.Hotel;
import fr.tourisme.react.restaurant.Restaurant;
import fr.tourisme.react.service.Service;

@Entity
public class Offre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nb_Personnes")
	private Integer nbPersonnes;

	public Integer getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(Integer nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	@Column
	private Double prix;

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/**
	 * 
	 * methode qui permet de calculer le prix de l'offre selon le type de Service ,
	 * Hotel Restaurant ou Activité
	 */
	public Double calculDuPrixFinal(String type) {

		Double retour = this.prix;

		// on va tester de quel type est le Service pour calculer son prix
		if (type.equals("Hotel")) {
			Hotel hotel = (Hotel) prestataire;
			return retour - (retour * hotel.getPourcentageMarge() / 100);

		} else if (type.equals("Restaurant")) {
			Restaurant restaurant = (Restaurant) prestataire;
			return retour - restaurant.getMargeFixe();

		} else if (type.equals("Activite")) {
			Activite activite = (Activite) prestataire;
			return retour - activite.getMargeNegociee();
		}

		return retour;

	}

	@Column
	private String descriptif;

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	// No serializer found for class
	// org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties
	// discovered to create BeanSerializer (to avoid exception, disable
	// SerializationFeature.FAIL_ON_EMPTY_BEANS)

	// chaque Offre est proposé par un seul prestataire
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
	@JoinColumn(name = "id_Service")
	@JsonBackReference(value="offre-service")  // annotation pour eviter les infinite recursion
	Service prestataire;

	public Service getPrestataire() {
		return prestataire;
	}

	public void setPrestataire(Service prestataire) {
		this.prestataire = prestataire;
	}

	@Override
	public int hashCode() {

		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Offre)) {
			return false;
		}
		Offre autre = (Offre) obj;

		return this.getId() == autre.getId();
	}

}
