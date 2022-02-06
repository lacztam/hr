package hu.webuni.hr.lacztam;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.Position;
import hu.webuni.hr.lacztam.repository.AverageSalaryByPosition;
import hu.webuni.hr.lacztam.repository.CompanyRepository;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.PositionRepository;
import hu.webuni.hr.lacztam.service.InitDbService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	private InitDbService initDbService;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.clearDB();
		initDbService.initDb();
		
		Company c1 = companyRepository.findByName("Elso ceg");
		
		Employee e1 = employeeRepository.findByNameStartingWithIgnoreCase("Anna").get(0);
		e1.setCompany(c1);
		employeeRepository.save(e1);
		
	}
}
