package hu.webuni.hr.lacztam.service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;

@Service
public class VacationPlannerService {

	@Autowired VacationRepository vacationRepository;
	@Autowired EmployeeRepository employeeRepository;
	
	public void initVacationDb() {
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
	}
	
	@Transactional
	public VacationPlanner addVacation(VacationPlanner vacationPlanner, Long vacationClaimerId) {
		Employee vacationClaimer = employeeRepository.findById(vacationClaimerId).get();
		vacationClaimer.addVacationToVacationPlannerList(vacationPlanner);
		vacationPlanner.setDateOfSubmission(LocalDateTime.now());
		return vacationRepository.save(vacationPlanner);
	}
	
	@Transactional
	public VacationPlanner approveVacation(long vacationId, long approverId, boolean state) {
		VacationPlanner vacation = vacationRepository.findById(vacationId).get();
		System.out.println("vacation.getVacationClaimer().getName():" + vacation.getVacationClaimer().getName());
		Employee claimer = vacation.getVacationClaimer();
		Employee principal = claimer.getManager();
		
		if(principal == null) 
			throw new org.springframework.security.access.AccessDeniedException("Manager invalid.");
		
		claimer.setManager(employeeRepository.findById(approverId).get());
		System.out.println("claimer.getManager().getName():" + claimer.getManager().getName());
		
		vacation.setPrincipal(claimer.getManager());
		vacation.setDateOfApprove(LocalDateTime.now());
		vacation.setState(state);
		return vacationRepository.save(vacation);
	}
	
}
