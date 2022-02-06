package hu.webuni.hr.lacztam.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.lacztam.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findByMonthlySalaryGreaterThan(Integer minSalay);
	
	List<Employee> findByPositionName(String title);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByBeginningOfEmploymentBetween(LocalDateTime start, LocalDateTime end);
}
