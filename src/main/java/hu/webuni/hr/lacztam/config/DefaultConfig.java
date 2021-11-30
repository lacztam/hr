package hu.webuni.hr.lacztam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.lacztam.service.DefaultEmployeeService;
import hu.webuni.hr.lacztam.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultConfig {

	@Bean
	EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
