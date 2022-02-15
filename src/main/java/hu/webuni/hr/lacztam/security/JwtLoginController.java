package hu.webuni.hr.lacztam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.model.Employee;

@RestController
public class JwtLoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	HrConfigProperties config;
	
	@PostMapping("/api/login")
	public String login(@RequestBody Employee employee) {
		Authentication authenticate = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(employee.getUsername(), employee.getPassword()));
		
		return jwtService.createJwtToken((UserDetails)authenticate.getPrincipal());
	}
	
}
