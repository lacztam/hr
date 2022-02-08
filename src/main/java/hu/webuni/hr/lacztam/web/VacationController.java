package hu.webuni.hr.lacztam.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;

@RestController
@RequestMapping("/api/vacations")
public class VacationController {
	
	@Autowired
	VacationRepository vacationRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/{id}")
	public VacationPlanner vacationById(@PathVariable Long id){
		List<Employee> emps = employeeRepository.findAll();
		Employee claimer = emps.get(0);
		Employee principal = emps.get(1);
		
		LocalDateTime vacationStart = LocalDateTime.now().plusMonths(2);
		LocalDateTime vacationEnd = LocalDateTime.now().plusMonths(2).plusDays(14);
		
		VacationPlanner newVacation = new VacationPlanner(1L, claimer, null, principal ,vacationStart, vacationEnd);
		
		vacationRepository.save(newVacation);
		
		return vacationRepository.findById(1L).get();
	}

}
