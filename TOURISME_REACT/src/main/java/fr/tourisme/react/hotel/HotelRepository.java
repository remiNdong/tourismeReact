package fr.tourisme.react.hotel;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

	@Query(value = "select h.* from hotel h , service s where h.id=s.id and s.ville=:villeChoisie", nativeQuery = true)
	public List<Hotel> findByVille(@Param("villeChoisie")String villeChoisie);
	
	@Query(value = "select h.* from hotel h , service s where h.id=s.id  and h.classement>=:classementChoisi", nativeQuery = true)
	public List<Hotel> findByEtoiles(@Param("classementChoisi")Integer classementChoisi);
	
	@Query(value = "select h.* from hotel h , service s where h.id=s.id  and s.ville=:villeChoisie and h.classement>=:classementChoisi", nativeQuery = true)
	public List<Hotel> findByVilleEtoiles(@Param("villeChoisie")String villeChoisie,@Param("classementChoisi")String classementChoisi);

}
