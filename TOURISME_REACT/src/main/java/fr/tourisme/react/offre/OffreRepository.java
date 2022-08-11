package fr.tourisme.react.offre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OffreRepository extends CrudRepository<Offre, Long>{

}
