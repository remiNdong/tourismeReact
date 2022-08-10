package fr.tourisme.react.restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
	
	public List<Restaurant> findByAdresseVille(String villeChoisie);
	
	public List<Restaurant> findByTerrasse(String terrasseChoisie);
	
	@Query("select distinct r from Restaurant r where r.adresse.ville=:villeChoisie  and r.terrasse=:terrasseChoisie")
	public List<Restaurant> findByAdresseVilleAndTerrasse(String villeChoisie,String terrasseChoisie);

}
