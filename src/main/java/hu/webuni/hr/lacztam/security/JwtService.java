package hu.webuni.hr.lacztam.security;

import java.io.ObjectInputFilter.Config;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.config.HrConfigProperties.JwtTokenProperties;
//import hu.webuni.hr.lacztam.config.HrConfigProperties.JwtTokenProperties;
import hu.webuni.hr.lacztam.model.Employee;

@Service
public class JwtService {

	@Autowired HrConfigProperties config;
	
	public String createJwtToken(UserDetails userDetails) {

		return JWT.create()
				.withSubject(userDetails.getUsername())
				.withArrayClaim(config.getJwtTokenProperties().getAuth(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(config.getJwtTokenProperties().getExpireTime())
				.withIssuer(config.getJwtTokenProperties().getIssuer())
				.sign(config.getJwtTokenProperties().getAlgorithm());
	}

	public UserDetails parseJwt(String jwToken) {
	
		DecodedJWT decodedJwt = JWT.require(config.getJwtTokenProperties().getAlgorithm())
			.withIssuer(config.getJwtTokenProperties().getIssuer())
			.build()
			.verify(jwToken);
		
		return new User(
				decodedJwt.getSubject(), 
				"dummy", 
				decodedJwt.getClaim(config.getJwtTokenProperties().getAuth()).asList(String.class)
				.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
				);
	}
	
}
