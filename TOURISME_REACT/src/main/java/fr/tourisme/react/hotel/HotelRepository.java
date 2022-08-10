package fr.tourisme.react.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
	


	public List<Hotel> findByAdresseVille(String villeChoisie);
	
	@Query("select distinct h from Hotel h where h.classement>=:classementChoisi")
	public List<Hotel> findByClassement(Integer classementChoisi);
	
	@Query("select distinct h from Hotel h where h.adresse.ville=:villeChoisie  and h.classement>=:classementChoisi")
	public List<Hotel> findByAdresseVilleAndClassement(String villeChoisie,Integer classementChoisi);

}
