package hu.webuni.hr.lacztam;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.repository.CompanyRepository;
import hu.webuni.hr.lacztam.service.InitDbService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	private InitDbService initDbService;
	
	@Autowired
	CompanyRepository companyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.clearDB();
		initDbService.inserTestData();
		
		List<Company> companies = (List<Company>) companyRepository.companiesThatHaveEmployeesMonthlySalaryGreaterThan(600000);
		System.out.println(companies.get(1).getName());
	}
}
