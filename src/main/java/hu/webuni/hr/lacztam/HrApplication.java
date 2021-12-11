package hu.webuni.hr.lacztam;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LocalDateTime ldt1 = LocalDateTime.of(2021, 11, 20, 0, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 12, 0, 0);
		LocalDateTime ldt3 = LocalDateTime.of(2018, 7, 1, 0, 0);
		LocalDateTime ldt4 = LocalDateTime.of(2014, 8, 22, 0, 0);
		LocalDateTime ldt5 = LocalDateTime.of(2010, 4, 17, 0, 0);

		Employee e5 = new Employee(1l, "Panna", "Adminisztrátor", 1000 ,ldt1);
		Employee e6 = new Employee(2l, "Anna", "PM", 2000, ldt2);
		Employee e7 = new  Employee(3l, "Feri", "Logisztikus", 3000, ldt3);
		Employee e8 = new Employee(4l, "Tamás", "Architech", 4000, ldt4);
		Employee e9 = new Employee(5l, "Ági", "Boss", 5000, ldt5);
		
		System.out.println(e6.toString());
		salaryService.setSalaryService(e6);
		System.out.println("Current salary: " + e6.getMonthlySalary() + "\n");

		System.out.println(e7.toString());
		salaryService.setSalaryService(e7);
		System.out.println("Current salary: " + e7.getMonthlySalary() + "\n");

		System.out.println(e8.toString());
		salaryService.setSalaryService(e8);
		System.out.println("Current salary: " + e8.getMonthlySalary() + "\n");

		System.out.println(e9.toString());
		salaryService.setSalaryService(e9);
		System.out.println("Current salary: " + e9.getMonthlySalary() + "\n");
		
		System.out.println(e5.toString());
		salaryService.setSalaryService(e5);
		System.out.println("Current salary: " + e5.getMonthlySalary() + "\n");
	}
}
