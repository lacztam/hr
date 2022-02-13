package hu.webuni.hr.lacztam.service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.lacztam.dto.VacationPlannerFilterDto;
import hu.webuni.hr.lacztam.mapper.VacationPlannerMapper;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.model.VacationState;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;

@Service
public class VacationPlannerService {

	@Autowired VacationRepository vacationRepository;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired VacationPlannerMapper vacationPlannerMapper;
	
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
	public VacationPlanner approveVacation(long vacationId, long approverId, VacationState vacationState) {
		VacationPlanner vacation = vacationRepository.findById(vacationId).get();

		Employee claimer = vacation.getVacationClaimer();
		Employee principal = vacation.getPrincipal();
		
		if(principal == null || !principal.getEmployeeId().equals(approverId)) 
			throw new org.springframework.security.access.AccessDeniedException("Manager invalid.");
		
		claimer.setManager(employeeRepository.findById(approverId).get());
		
		vacation.setPrincipal(claimer.getManager());
		vacation.setDateOfApprove(LocalDateTime.now());
		vacation.setVacationState(vacationState);
		return vacationRepository.save(vacation);
	}
	
	@Transactional
	public VacationPlanner modifyVacation(Long vacationId, VacationPlanner vacationPlanner) {
		VacationPlanner existingVacation = vacationRepository.findById(vacationId).get();
		
		if(existingVacation != null) {
			existingVacation.setPrincipal(vacationPlanner.getPrincipal());
			existingVacation.setDateOfSubmission(vacationPlanner.getDateOfSubmission());
			existingVacation.setVacationStart(vacationPlanner.getVacationStart());
			existingVacation.setVacationEnd(vacationPlanner.getVacationEnd());
			existingVacation.setVacationState((vacationPlanner.getVacationState()));
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return existingVacation;
	}
	
	public List<VacationPlannerFilterDto> getSpecifiedVacations(
										String state,
										String name,
										LocalDate submissionStart,
										LocalDate submissionEnd,
										LocalDate vacationStart,
										LocalDate vacationEnd
										){
		
		Specification<VacationPlanner> spec = Specification.where(null);
		
		if(StringUtils.hasText(state))
			spec = spec.and(VacationPlanerSpecification.hasState(state));
		
		if(StringUtils.hasText(name))
			spec = spec.and(VacationPlanerSpecification.hasState(name));
		
		if(submissionStart != null && submissionEnd != null)
			spec = spec.and(VacationPlanerSpecification.hasSubmissionDate(submissionStart, submissionEnd));

		if(vacationStart != null && vacationEnd != null && vacationStart.isBefore(vacationEnd)) {
			Specification<VacationPlanner> startVacations = VacationPlanerSpecification.hasVacationStartDateBetween(vacationStart, vacationEnd);
			Specification<VacationPlanner> endVacations = VacationPlanerSpecification.hasVacationEndDateBetween(vacationStart, vacationEnd);
			spec = spec.and(startVacations).or(endVacations);
		}
		
		
		return vacationPlannerMapper.vacationsToFilterDtos(vacationRepository.findAll(spec));
	}
	
}
