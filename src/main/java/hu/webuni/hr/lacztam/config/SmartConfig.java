package hu.webuni.hr.lacztam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.lacztam.service.AbstractEmployeePayService;
import hu.webuni.hr.lacztam.service.EmployeeService;
import hu.webuni.hr.lacztam.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartConfig {

	@Bean
	public EmployeeService employeeService() {
		return new SmartEmployeeService();
	}
}
