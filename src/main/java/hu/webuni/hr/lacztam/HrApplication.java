package hu.webuni.hr.lacztam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.service.SalaryService;
import hu.webuni.hr.lacztam.service.SmartEmployeeService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee e6 = new Employee(6l, "Anna", "PM", 2000, 2020, 10, 12);
		Employee e7 = new Employee(7l, "Feri", "Logisztikus", 3000, 2018, 7, 12);
		Employee e8 = new Employee(8l, "Tamás", "Architech", 4000, 2014, 8, 22);
		Employee e9 = new Employee(9l, "Ági", "Boss", 5000, 2010, 4, 30);

		System.out.println(e6.toString());
		salaryService.setSalaryService(e6);
		System.out.println(e6.getMonthlySalary() + "\n");

		System.out.println(e7.toString());
		salaryService.setSalaryService(e7);
		System.out.println(e7.getMonthlySalary() + "\n");

		System.out.println(e8.toString());
		salaryService.setSalaryService(e8);
		System.out.println(e8.getMonthlySalary() + "\n");

		System.out.println(e9.toString());
		salaryService.setSalaryService(e9);
		System.out.println(e9.getMonthlySalary() + "\n");
	}
}
