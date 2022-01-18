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
	
	List<Employee> findByTitle(String title);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByBeginningOfEmploymentBetween(LocalDateTime start, LocalDateTime end);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO public.employee(\r\n"
			+ "	id, beginning_of_employment, monthly_salary, name, title, company_company_registration_number)\r\n"
			+ "VALUES \r\n"
			+ "	(1, '2010-01-01T12:46:40', 250000, 'Klára', 'Logisztikus', 1),\r\n"
			+ "	(2, '2012-03-02T13:50:40', 300000, 'Ildikó', 'PM', 1),\r\n"
			+ "	(3, '2015-05-03T14:13:40', 450000, 'Péter', 'Developer', 1),\r\n"
			+ "	(4, '2017-04-05T15:19:40', 200000, 'Gábor', 'Logisztikus', 2),\r\n"
			+ "	(5, '2018-10-06T16:40:40', 220000, 'Szilvia', 'Könyvelő', 2),\r\n"
			+ "	(6, '2018-12-10T18:55:40', 370000, 'Bernadett', 'Titkárnő', 2),\r\n"
			+ "	(7, '2019-08-12T19:36:40', 500000, 'Balázs', 'Tanácsadó', 3),\r\n"
			+ "	(8, '2020-07-13T20:26:40', 550000, 'László', 'Kivitelezés vezető', 3),\r\n"
			+ "	(9, '2021-05-15T05:16:40', 750000, 'Panna', 'CEO', 3);", 
				nativeQuery = true)
	public void insertEntiresIntoEmployee();
	
}
