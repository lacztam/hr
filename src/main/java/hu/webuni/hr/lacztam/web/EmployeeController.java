package hu.webuni.hr.lacztam.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.mapper.EmployeeMapper;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping
	public String getAllEmployees(Map<String, Object> model) {
		model.put("employees", employeeRepository.findAll());
		model.put("newEmployee", new EmployeeDto());
		
		return "employees";
	}
	
	@PostMapping
	public String addEmployee(@Valid EmployeeDto employeeDto){
		employeeRepository.save(employeeMapper.dtoToEmployee(employeeDto));
		return "redirect:employees";
	}
	
	@GetMapping("/modify/{id}")
	public String getEmployee(
			@PathVariable long id,
			Map<String, Object> model) {

		Employee employee = employeeRepository.findById(id).orElseThrow();
		model.put("targetEmployee", employee);
		
		return "modify_employee";
	}
	
	@PostMapping("/modify/{id}")
	public String modifyEmployee(@PathVariable long id, EmployeeDto employeeDto){
		
		if(employeeDto != null) {
			employeeDto.setId(id);
			employeeRepository.save(employeeMapper.dtoToEmployee(employeeDto));
		}
				
		return "redirect:../";	
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow();
		employeeRepository.delete(employee);
		
		return "redirect:../";
	}
}
