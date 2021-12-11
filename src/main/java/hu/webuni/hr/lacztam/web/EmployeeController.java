package hu.webuni.hr.lacztam.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	Map<Long, Employee> employeesMap = new HashMap();
	LocalDateTime ldt1 = LocalDateTime.of(2021, 11, 20, 0, 0);
	{
		LocalDateTime ldt1 = LocalDateTime.of(2021, 11, 20, 0, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 12, 0, 0);
		LocalDateTime ldt3 = LocalDateTime.of(2018, 7, 1, 0, 0);
		LocalDateTime ldt4 = LocalDateTime.of(2014, 8, 22, 0, 0);
		LocalDateTime ldt5 = LocalDateTime.of(2010, 4, 17, 0, 0);
		employeesMap.put(1l, new Employee(1l, "Panna", "Adminisztrátor", 1000 ,ldt1));
		employeesMap.put(1l, new Employee(1l, "Kanna", "PM", 1500 ,ldt1));
		employeesMap.put(2l, new Employee(2l, "Anna", "PM", 2000, ldt2));
		employeesMap.put(3l, new Employee(3l, "Feri", "Logisztikus", 3000, ldt3));
		employeesMap.put(4l, new Employee(4l, "Tamás", "Architech", 4000, ldt4));
		employeesMap.put(5l, new Employee(5l, "Ági", "Boss", 5000, ldt5));
	}
	
	@GetMapping
	public String getAllEmployees(Map<String, Object> model) {
		model.put("employees", new ArrayList<>(employeesMap.values()) );
		model.put("newEmployee", new Employee());
		return "employees";
	}
	
	@PostMapping
	public String addEmployee(Employee employee){
		employeesMap.put(employee.getId(), employee);
		return "redirect:employees";
	}
	
	@GetMapping("/modify/{id}")
	public String getEmployee(
			@PathVariable long id,
			Map<String, Object> model) {

		Employee employee = employeesMap.get(id);
		model.put("targetEmployee", employee);
		
		return "modify_employee";
	}
	

	@PostMapping("/modify/{id}")
	public String modifyEmployee(Employee targetEmployee, @PathVariable long id){
		
		if(targetEmployee != null) {
			targetEmployee.setId(id);
			employeesMap.put(targetEmployee.getId(), targetEmployee);
		}
				
		return "redirect:../";	
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		employeesMap.remove(employeesMap.get(id).getId());
		return "redirect:../";
	}
}
