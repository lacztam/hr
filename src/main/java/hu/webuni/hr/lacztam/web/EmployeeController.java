package hu.webuni.hr.lacztam.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.lacztam.model.Employee;

@Controller
public class EmployeeController {

	Map<Long, Employee> employees = new HashMap();
	
	{
		employees.put(1l, new Employee(1l, "Panna", "Adminisztrátor", 1000 ,2021, 11, 20));
		employees.put(2l, new Employee(2l, "Anna", "PM", 2000, 2020, 10, 12));
		employees.put(3l, new Employee(3l, "Feri", "Logisztikus", 3000, 2018, 7, 12));
		employees.put(4l, new Employee(4l, "Tamás", "Architech", 4000, 2014, 8, 22));
		employees.put(5l, new Employee(5l, "Ági", "Boss", 5000, 2010, 4, 30));
	}
	
	@GetMapping("/employees")
	public String getAllEmployees(Map<String, Object> model) {
		model.put("employees", new ArrayList<>(employees.values()) );
		model.put("newEmployee", new Employee());
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(
			Employee employee, 
			@RequestParam(value = "year", required = false) String year, 
			@RequestParam(value = "month", required = false) String month, 
			@RequestParam(value = "day", required = false) String day) {
		
		if( year.equals(null) || year == "") {
			LocalDateTime currentDate = LocalDateTime.now();
			year = currentDate.getYear() + "";
			month = currentDate.getMonthValue() + "";
			day = currentDate.getDayOfMonth() + "";
		}
		
		if(!employee.getName().equals(null) || employee.getName() == "") {
			employees.put(
					employee.getId(), 
					new Employee(
						employee.getId(),
						employee.getName(),
						employee.getTitle(),
						employee.getMonthlySalary(),
						Integer.valueOf(year),
						Integer.valueOf(month),
						Integer.valueOf(day)
					)
			);
			return "redirect:employees";
		}else {
			System.out.println("Name cannot be empty.");
		}
		return "employees";
	}
}
