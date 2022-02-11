package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.model.VacationPlanner_;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;


public class VacationSpecification {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public static Specification<VacationPlanner> hasState(Boolean state){
		return (root, cq, cb) -> cb.equal(root.get(VacationPlanner_.state), state); 
	}
	
	public static Specification<VacationPlanner> hasName(String name){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(VacationPlanner_.principal.getName())), (name + "%").toLowerCase());	
	}

	public static Specification<VacationPlanner> hasBetweenSubmission(LocalDateTime startBetweenSubmission, LocalDateTime endBetweenSubmission){
		return (root, cq, cb) -> cb.between(root.get(VacationPlanner_.dateOfSubmission), startBetweenSubmission, endBetweenSubmission);
	}
	
//	public static Specification<VacationPlanner> hasBetweenVacationDate(LocalDateTime startVacation, LocalDateTime endVacation){
//		return (root, cq, cb) -> cb.equal(root.get(VacationPlanner_.), startBetweenSubmission, endBetweenSubmission);
//	}
	
//	private Boolean checkDate(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime startVacation, LocalDateTime endVacation) {
//		
//		boolean isOkayEndDate = (endDate.isAfter(startVacation) && endDate.isAfter(endVacation)) || 
//										(endDate.isAfter(startVacation) && endDate.isBefore(endVacation));
//		boolean isOkayStartDate = startDate.isAfter(startVacation) && startDate.isBefore(endVacation);
//		
//		return (isOkayStartDate || isOkayEndDate);
//	}
}
