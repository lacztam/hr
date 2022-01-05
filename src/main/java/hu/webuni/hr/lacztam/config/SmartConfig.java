package hu.webuni.hr.lacztam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.lacztam.service.EmployeePayService;
import hu.webuni.hr.lacztam.service.EmployeeService;
import hu.webuni.hr.lacztam.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartConfig {

	@Bean
	EmployeePayService employeePayService() {
		return new SmartEmployeeService();
	}
}
