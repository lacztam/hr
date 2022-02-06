package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.Employee_;

public class EmployeeSpecification {
	
	public static Specification<Employee> hasId(Long id){
		return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
	}
	
	public static Specification<Employee> hasName(String name){
		return (root, cq, cb) -> cb.like(root.get(Employee_.name), name + "%" );
	}

	public static Specification<Employee> hasSalary(int salary){
		return (root, cq, cb) -> cb.between(root.get(Employee_.monthlySalary), 
				salaryMinus5Percentage(salary), salaryPlus5Percentage(salary));
	}

	public static Specification<Employee> hasPosition(String positionName){
		return (root, cq, cb) -> cb.equal(root.get(Employee_.position.getName()), positionName);
	}

	public static Specification<Employee> hasEntryDate(LocalDateTime entryDate){
		LocalDateTime startOfDay = LocalDateTime.of(entryDate.toLocalDate(), LocalTime.of(0, 0));
		LocalDateTime endOfDay = LocalDateTime.of(entryDate.toLocalDate(), LocalTime.of(23, 59));
		
		return (root, cq, cb) -> cb.between(root.get(Employee_.beginningOfEmployment), startOfDay, endOfDay);
	}	
	
	public static Specification<Employee> hasCompany(String companyName){
		return (root, cq, cb) -> cb.like(root.get(Employee_.company.getName()), companyName + "%");
	}
	
	private static int salaryMinus5Percentage(int salary) {
		return (int)(Double.valueOf(salary) - Double.valueOf(salary) * 0.05);
	}
	
	private static int salaryPlus5Percentage(int salary) {
		return (int)(Double.valueOf(salary) * 1.05);
	}
}
