package fr.tourisme.react.hotel;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class HotelController {
	
	@Autowired
    private HotelRepository hotelRepository;
	
	  @GetMapping(value = "/hotels")
	    public ResponseEntity list() {
		  
		  Iterable<Hotel> hotels=hotelRepository.findAll();
		  return new ResponseEntity(hotels, HttpStatus.OK);
	  }

}
