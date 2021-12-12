package hu.webuni.hr.lacztam.web;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.DataWrapper;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	Map<Long, EmployeeDto> employeesMap = new DataWrapper().getEmployeesMap();
	
	@GetMapping
	public String getAllEmployees(Map<String, Object> model) {
		model.put("employees", new ArrayList<>(employeesMap.values()) );
		model.put("newEmployee", new EmployeeDto());
		return "employees";
	}
	
	@PostMapping
	public String addEmployee(EmployeeDto employeeDto){
		employeesMap.put(employeeDto.getId(), employeeDto);
		return "redirect:employees";
	}
	
	@GetMapping("/modify/{id}")
	public String getEmployee(
			@PathVariable long id,
			Map<String, Object> model) {

		EmployeeDto EmployeeDto = employeesMap.get(id);
		model.put("targetEmployee", EmployeeDto);
		
		return "modify_employee";
	}
	

	@PostMapping("/modify/{id}")
	public String modifyEmployee(EmployeeDto targetEmployee, @PathVariable long id){
		
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
