package fr.tourisme.react.activite;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class ActiviteController {
	
	@Autowired
    private ActiviteRepository activiteRepository;
	

	  @GetMapping(value = "/activites")
	    public ResponseEntity list(@RequestParam(required = false) String villeChoisie, @RequestParam(required = false) String typeChoisi) {
		  
		 Iterable<Activite> activites;
		 if(villeChoisie!=null && typeChoisi!=null)
			 activites=activiteRepository.findByAdresseVilleType(villeChoisie, typeChoisi);
		 else if(typeChoisi!=null)
			 activites=activiteRepository.findByType(typeChoisi);
		 else if(villeChoisie!=null)
			 activites=activiteRepository.findByAdresseVille(villeChoisie);
		 else
			 activites=activiteRepository.findAll();
		 
		 return new ResponseEntity(activites, HttpStatus.OK);
	
	  }
	  

	    @GetMapping("/activites/{activiteId}")
	    public ResponseEntity loadActivite(@PathVariable("activiteId") String activiteId) {
	        Optional<Activite> activite = activiteRepository.findById(Long.valueOf(activiteId));
	        if(!activite.isPresent()){
	            return new ResponseEntity("Activite not found", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity(activite.get(), HttpStatus.OK);
	    }

}
