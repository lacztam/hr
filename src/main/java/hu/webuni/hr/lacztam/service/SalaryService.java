package hu.webuni.hr.lacztam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Employee;

@Service
public class SalaryService {

	@Autowired
	EmployeeService employeeService;

	public void setSalaryService(Employee employee) {
		int originalSalary = employee.getMonthlySalary();
		
		employee.setMonthlySalary(
				(int) (employee.getMonthlySalary() * (1.0 + employeeService.getPayRaisePercent(employee) / 100.0)));
		
		int afterSetMonthlySalary = employee.getMonthlySalary();

		if (originalSalary != afterSetMonthlySalary) 
			System.out.println("Salary successfully updated.");
		else 
			System.out.println("The salary has not changed.");
	}
}
