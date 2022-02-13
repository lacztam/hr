package hu.webuni.hr.lacztam.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.CompanyType;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.Position;
import hu.webuni.hr.lacztam.model.Qualification;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.model.VacationState;
import hu.webuni.hr.lacztam.repository.CompanyRepository;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.PositionRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;

@Service
public class InitDbService {

	@Autowired EmployeeRepository employeeRepository;
	@Autowired CompanyRepository companyRepository;
	@Autowired PositionRepository positionRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired VacationRepository vacationRepository;
	public void clearDB() {
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
		positionRepository.deleteAll();
	}
	
	public void initDb() {
		String developerName = "Fejlesztő";
		String testerName = "Tesztelő";
	
		List<Position> developerPositions = positionRepository.findByName(developerName);
		Position developer = developerPositions.isEmpty()
				? positionRepository.save(new Position(developerName, Qualification.UNIVERSITY, 350000))
				: developerPositions.get(0);
	
		List<Position> testerPositions = positionRepository.findByName(testerName);
		Position tester = testerPositions.isEmpty()
				? positionRepository.save(new Position(testerName, Qualification.UNIVERSITY, 200000))
				: testerPositions.get(0);
		
		positionRepository.save(new Position("PM", Qualification.COLLEGE, 400000));
		positionRepository.save(new Position("Logisztikus", Qualification.HIGH_SCHOOL, 275000));
		positionRepository.save(new Position("Könyvelő", Qualification.COLLEGE, 250000));
		positionRepository.save(new Position("CEO", Qualification.UNIVERSITY, 500000));
		positionRepository.save(new Position("Tanácsadó", Qualification.PHD, 700000));
		
		Company c1 = new Company(1L, 1111, "Elso Ceg", "Szegd Okos utca 12345", CompanyType.KFT, null);
		Company c2 = new Company(2L, 2222, "Masodik Ceg", "Budapest, Tudós körút 2", CompanyType.NYRT, null); 
		Company c3 = new Company(3L, 3333, "Harmadik Ceg", "Székesfehérvár, Szép utca 3", CompanyType.ZRT, null);
		
		companyRepository.save(c1);
		companyRepository.save(c2);
		companyRepository.save(c3);
		
		Employee e1 = new Employee(1L, "Klára", 250000, LocalDateTime.of(2010, 1, 1, 01, 01, 01) );
		e1.setCompany(companyRepository.findByName("Elso Ceg"));
		e1.setPosition(positionRepository.findByName("Fejlesztő").get(0));
		e1.setUsername("user");
		e1.setPassword(passwordEncoder.encode("pw"));
		employeeRepository.save(e1);
		
		Employee e2 = new Employee(2L, "Ildikó", 300000, LocalDateTime.of(2011, 3, 04, 01, 01, 01) );
		e2.setCompany(companyRepository.findByName("Elso Ceg"));
		e2.setPosition(positionRepository.findByName("Tesztelő").get(0));
		e2.setUsername("admin");
		e2.setPassword(passwordEncoder.encode("pw"));
		employeeRepository.save(e2);
		
		Employee e3 = new Employee(3L, "Péter", 450000, LocalDateTime.of(2012, 5, 6, 01, 01, 01) ); 
		e3.setCompany(companyRepository.findByName("Elso Ceg"));
		e3.setPosition(positionRepository.findByName("PM").get(0));
		employeeRepository.save(e3);
		
		Employee e4 = new Employee(4L, "Gábor", 200000, LocalDateTime.of(2013, 7, 8, 01, 01, 01) );
		e4.setCompany(companyRepository.findByName("Masodik Ceg"));
		e4.setPosition(positionRepository.findByName("Logisztikus").get(0));
		employeeRepository.save(e4);
		
		Employee e5 = new Employee(5L, "Szilvia", 220000, LocalDateTime.of(2014, 10, 9, 01, 01, 01) );
		e5.setCompany(companyRepository.findByName("Masodik Ceg"));
		e5.setPosition(positionRepository.findByName("Könyvelő").get(0));
		employeeRepository.save(e5);
		
		Employee e6 = new Employee(6L, "Balázs", 900000, LocalDateTime.of(2015, 11, 12, 01, 01, 01) );
		e6.setCompany(companyRepository.findByName("Masodik Ceg"));
		e6.setPosition(positionRepository.findByName("CEO").get(0));
		employeeRepository.save(e6);
		
		Employee e7 = new Employee(7L, "Anna", 750000, LocalDateTime.of(2016, 2, 3, 01, 01, 01) );
		e7.setCompany(companyRepository.findByName("Harmadik Ceg"));
		e7.setPosition(positionRepository.findByName("Tanácsadó").get(0));
		employeeRepository.save(e7);	
		
		VacationPlanner vacationPlanner = new VacationPlanner();
		vacationPlanner.setDateOfSubmission(LocalDateTime.now());
		Employee principal = employeeRepository.findByNameStartingWithIgnoreCase("Ildikó").get(0);
		Employee claimer = employeeRepository.findByNameStartingWithIgnoreCase("Klára").get(0);
		vacationPlanner.setPrincipal(principal);
		vacationPlanner.setVacationClaimer(claimer);
		vacationPlanner.setVacationStart(LocalDate.of(2022,03,10));
		vacationPlanner.setVacationEnd(LocalDate.of(2022, 03, 20));
		vacationPlanner.setVacationState(VacationState.PENDING);
		vacationRepository.save(vacationPlanner);
		VacationPlanner vacationPlanner2 = new VacationPlanner();
		vacationPlanner2.setVacationStart(LocalDate.of(2022,03,07));
		vacationPlanner2.setVacationEnd(LocalDate.of(2022, 03, 25));
		vacationPlanner2.setPrincipal(claimer);
		vacationPlanner2.setVacationClaimer(principal);
		vacationPlanner2.setDateOfSubmission(LocalDateTime.now());
		vacationPlanner2.setVacationState(VacationState.CANCELLED_BY_EMPLOYEE);
		vacationRepository.save(vacationPlanner2);
	}
}
