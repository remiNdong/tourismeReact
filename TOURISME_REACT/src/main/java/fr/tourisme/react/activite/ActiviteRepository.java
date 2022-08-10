package fr.tourisme.react.activite;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface ActiviteRepository extends CrudRepository<Activite, Long>{
	
	public List<Activite> findByAdresseVille(String villeChoisie);
	
	public List<Activite> findByType(String typeChoisi);
	

	@Query("select distinct a from Activite a where a.adresse.ville=:villeChoisie  and a.type=:typeChoisi")
	public List<Activite> findByAdresseVilleType(String villeChoisie,String typeChoisi);

}
