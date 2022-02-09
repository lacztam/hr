package hu.webuni.hr.lacztam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//user kezelés, in memory, bryptencodert be kell állítani
	//egyébként lehet a password()-ben sima string-ként megadni a jelszót
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("user").authorities("user").password(passwordEncoder().encode("pw"))
			.and()
			.withUser("admin").authorities("user", "admin").password(passwordEncoder().encode("pw"));
	}

	
	//szabályokat
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic() // basic authentikáció
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // REST apinál ezt alkalmazzuk leggyakrabban
//			.and()
//			.authorizeHttpRequests() //védettek legyen bizonyos url ek
//			.antMatchers(HttpMethod.POST, "/api/companies/**").hasAuthority("admin") // POST olásra, csak admin mehet a /api/companies oldalra és aloldalakra
//			.antMatchers(HttpMethod.PUT, "/api/companies/**").hasAnyAuthority("user", "admin")
//			.anyRequest().authenticated(); 
			;
	}
	
}
