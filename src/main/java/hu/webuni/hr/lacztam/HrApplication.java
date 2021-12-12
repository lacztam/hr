package hu.webuni.hr.lacztam;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.DataWrapper;
import hu.webuni.hr.lacztam.service.EmployeeService;
import hu.webuni.hr.lacztam.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	EmployeeService employeeService;
		
	Map<Long, EmployeeDto> employeesMap = new DataWrapper().getEmployeesMap();
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		EmployeeDto e1 = employeesMap.get(1L);
		EmployeeDto e2 = employeesMap.get(2L);
		EmployeeDto e3 = employeesMap.get(3L);
		EmployeeDto e4 = employeesMap.get(4L);
		EmployeeDto e5 = employeesMap.get(5L);
		
		System.out.println(e1);
		salaryService.setSalaryService(e1);
		System.out.println("Current salary: " + e1.getMonthlySalary() + "\n");

		System.out.println(e2.toString());
		salaryService.setSalaryService(e2);
		System.out.println("Current salary: " + e2.getMonthlySalary() + "\n");

		System.out.println(e3.toString());
		salaryService.setSalaryService(e3);
		System.out.println("Current salary: " + e3.getMonthlySalary() + "\n");

		System.out.println(e4.toString());
		salaryService.setSalaryService(e4);
		System.out.println("Current salary: " + e4.getMonthlySalary() + "\n");
		
		System.out.println(e5.toString());
		salaryService.setSalaryService(e5);
		System.out.println("Current salary: " + e5.getMonthlySalary());
		System.out.println("getPayRaisePercent(e9):" + employeeService.getPayRaisePercent(e5));
		
		System.out.println("employeeDto.getName()" + employeesMap.get(1L).getName());		
	}
}
