package hu.webuni.hr.lacztam.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.lacztam.dto.VacationPlannerDto;
import hu.webuni.hr.lacztam.mapper.VacationPlannerMapper;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;

@RestController
@RequestMapping("/api/vacations")
public class VacationController {
	
	@Autowired VacationRepository vacationRepository;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired VacationPlannerMapper VPmapper;
	
	@GetMapping
	public List<VacationPlannerDto> vacationById(){
		List<Employee> emps = employeeRepository.findAll();
		Employee claimer = emps.get(3);
		Employee principal = emps.get(4);
		
		LocalDate vacationStart = LocalDate.now().plusMonths(2);
		LocalDate vacationEnd = LocalDate.now().plusMonths(2).plusDays(14);
		
		VacationPlanner newVacation = new VacationPlanner();
		newVacation.setVacationClaimer(claimer);
		newVacation.setPrincipal(principal);
		newVacation.setDateOfSubmission(LocalDateTime.now());
		newVacation.setVacationStart(vacationStart);
		newVacation.setVacationEnd(vacationEnd);
		
		vacationRepository.save(newVacation);
		
		List<VacationPlannerDto> vacationPlanners = VPmapper.plannersToDtos(vacationRepository.findAll());
		return vacationPlanners;
	}

}
