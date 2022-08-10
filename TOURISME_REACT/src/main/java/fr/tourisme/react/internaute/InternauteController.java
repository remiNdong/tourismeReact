package fr.tourisme.react.internaute;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.tourisme.react.jwt.JwtController;
import fr.tourisme.react.jwt.JwtFilter;
import fr.tourisme.react.jwt.JwtUtils;



@RestController
public class InternauteController {
	
	@Autowired
	InternauteRepository internauteRepository;
	
	@Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtController jwtController;

    @PostMapping("/internautes")
    public ResponseEntity add(@Valid @RequestBody Internaute internaute) {

        Internaute existingInternaute = internauteRepository.findOneByEmail(internaute.getEmail());
        if(existingInternaute != null) {
            return new ResponseEntity("Internaute already existing", HttpStatus.BAD_REQUEST);
        }
        Internaute user = saveInternaute(internaute);
        Authentication authentication = jwtController.logUser(internaute.getEmail(), internaute.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/isConnected")
    public ResponseEntity getUSerConnected() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new ResponseEntity(((UserDetails) principal).getUsername(), HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected", HttpStatus.FORBIDDEN);
    }

    private Internaute saveInternaute(Internaute internaute) {
    	Internaute user = new Internaute();
        user.setEmail(internaute.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(internaute.getPassword()));
        user.setNom(StringUtils.capitalize(internaute.getNom()));
        user.setPrenom(StringUtils.capitalize(internaute.getPrenom()));
        internauteRepository.save(user);
        return user;
    }

}
