package fr.tourisme.react.hotel;



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
public class HotelController {
	
	@Autowired
    private HotelRepository hotelRepository;
	

	
	  @GetMapping(value = "/hotels")
	    public ResponseEntity list(@RequestParam(required = false) String villeChoisie,@RequestParam(required = false) String classementChoisi) {
		  
		 Iterable<Hotel> hotels;
		 if(villeChoisie!=null && classementChoisi!=null)
			 hotels=hotelRepository.findByAdresseVilleAndClassement(villeChoisie, Integer.valueOf(classementChoisi));
		 else if(villeChoisie!=null)
			 hotels=hotelRepository.findByAdresseVille(villeChoisie);
		 else if(classementChoisi!=null)
			 hotels=hotelRepository.findByClassement(Integer.valueOf(classementChoisi));
		 else
		 hotels=hotelRepository.findAll();
		 
		 return new ResponseEntity(hotels, HttpStatus.OK);
	
	  }
	  

	    @GetMapping("/hotels/{hotelsId}")
	    public ResponseEntity loadActivite(@PathVariable("hotelsId") String hotelsId) {
	        Optional<Hotel> hotel = hotelRepository.findById(Long.valueOf(hotelsId));
	        if(!hotel.isPresent()){
	            return new ResponseEntity("Hotel not found", HttpStatus.BAD_REQUEST);
	        }
	        return new ResponseEntity(hotel.get(), HttpStatus.OK);
	    }

}
