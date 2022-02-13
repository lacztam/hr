package hu.webuni.hr.lacztam.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.model.VacationPlanner_;
import hu.webuni.hr.lacztam.model.VacationState;

public class VacationPlanerSpecification {

	public static Specification<VacationPlanner> hasState(String state){
		return (root, cq, cb) -> cb.equal(root.get(VacationPlanner_.vacationState), VacationState.valueOf(state.toUpperCase()));
	}
	
	public static Specification<VacationPlanner> hasClaimerName(String name){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(VacationPlanner_.vacationClaimer.getName())), (name + "%" ).toLowerCase());
	}

	public static Specification<VacationPlanner> hasPrincipalName(String name){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(VacationPlanner_.principal.getName())), (name + "%" ).toLowerCase());
	}
	
	public static Specification<VacationPlanner> hasSubmissionDate(LocalDate start, LocalDate end){
		return (root, cq, cb) -> cb.between(
				root.get(VacationPlanner_.dateOfSubmission), 
				LocalDateTime.of(start, LocalTime.of(0, 0)), 
				LocalDateTime.of(end, LocalTime.of(0, 0))
				);
	}
	
	public static Specification<VacationPlanner> hasVacationStartLessThan(LocalDate time){
		return (root, cq, cb) -> cb.lessThan(root.get(VacationPlanner_.vacationStart), time);
	}
	
	public static Specification<VacationPlanner> hasVacationStartGreaterThan(LocalDate time){
		return (root, cq, cb) -> cb.greaterThan(root.get(VacationPlanner_.vacationStart), time);
	}
	
	public static Specification<VacationPlanner> hasVacationEndGreaterThan(LocalDate time){
		return (root, cq, cb) -> cb.greaterThan(root.get(VacationPlanner_.vacationEnd), time);
	}

	public static Specification<VacationPlanner> hasVacationEndLessThan(LocalDate time){
		return (root, cq, cb) -> cb.lessThan(root.get(VacationPlanner_.vacationEnd), time);
	}
	
	public static Specification<VacationPlanner> hasVacationStartDateBetween(LocalDate start, LocalDate end){
		return (root, cq, cb) -> cb.between(root.get(VacationPlanner_.vacationStart), start, end);	
	}

	public static Specification<VacationPlanner> hasVacationEndDateBetween(LocalDate start, LocalDate end){
		return (root, cq, cb) -> cb.between(root.get(VacationPlanner_.vacationEnd), start, end);	
	}
}
  