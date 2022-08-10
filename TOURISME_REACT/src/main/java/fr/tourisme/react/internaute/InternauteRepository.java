package fr.tourisme.react.internaute;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InternauteRepository extends CrudRepository<Internaute, Long> {

	Internaute findOneByEmail(String email);

}