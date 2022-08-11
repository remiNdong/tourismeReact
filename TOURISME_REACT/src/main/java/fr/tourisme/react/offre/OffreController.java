package fr.tourisme.react.offre;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class OffreController {
	
	@Autowired
    private OffreRepository offreRepository;
	

    @GetMapping("/offres/{offreId}")
    public ResponseEntity loadOffre(@PathVariable("offreId") String offreId) {
        Optional<Offre> offre = offreRepository.findById(Long.valueOf(offreId));
        if(!offre.isPresent()){
            return new ResponseEntity("Offre not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(offre.get(), HttpStatus.OK);
    }

}
