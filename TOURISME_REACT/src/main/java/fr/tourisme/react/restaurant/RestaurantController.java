package fr.tourisme.react.restaurant;

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
public class RestaurantController {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
	

	
	  @GetMapping(value = "/restaurants")
	    public ResponseEntity list(@RequestParam(required = false) String villeChoisie,@RequestParam(required = false) String terrasseChoisi) {
		  
		 Iterable<Restaurant> restaurants;
		 if(villeChoisie!=null && terrasseChoisi!=null)
			 restaurants=restaurantRepository.findByAdresseVilleAndTerrasse(villeChoisie,  terrasseChoisi);
		 else if(villeChoisie!=null)
			 restaurants=restaurantRepository.findByAdresseVille(villeChoisie);
		 else if(terrasseChoisi!=null)
			 restaurants=restaurantRepository.findByTerrasse(terrasseChoisi);
		 else
			 restaurants=restaurantRepository.findAll();
		 
		 return new ResponseEntity(restaurants, HttpStatus.OK);
	
	  }
	  

	    @GetMapping("/restaurants/{restaurantId}")
	    public ResponseEntity loadRestaurant(@PathVariable("restaurantId") String restaurantId) {
	        Optional<Restaurant> restaurant = restaurantRepository.findById(Long.valueOf(restaurantId));
	        if(!restaurant.isPresent()){
	            return new ResponseEntity("Restaurant not found", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity(restaurant.get(), HttpStatus.OK);
	    }

}

