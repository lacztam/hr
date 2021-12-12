package hu.webuni.hr.lacztam.service;

import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Service
public class SalaryService {

	EmployeeService employeeService;

	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setSalaryService(EmployeeDto employee) {
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
