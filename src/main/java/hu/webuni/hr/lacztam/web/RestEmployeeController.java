package hu.webuni.hr.lacztam.web;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.mapper.EmployeeMapper;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.service.EmployeeService;
import hu.webuni.hr.lacztam.service.validator.EmployeeDtoValidator;

@RestController
@RequestMapping("/api/employees")
public class RestEmployeeController {
	
	@Autowired
	EmployeeDtoValidator validator;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return employeeMapper.employeesToDtos(employeeService.findAll());
	}
	
	@GetMapping(params = "minSalary")
	public List<EmployeeDto> findBySalary(@RequestParam int minSalary){
		return employeeMapper.employeesToDtos(employeeService.getEmployeesMap().values().stream()
				.filter(e -> e.getMonthlySalary() > minSalary)
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		Employee employee = employeeService.findById(id);
		
		if(employee != null)
			return employeeMapper.employeeToDto(employee);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public EmployeeDto createNewEmployeeDto(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) {
		Employee employee = employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		
//		Ez is jó!
//		validator.validate(employeeDto, result);
		
		if(result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		
		return employeeMapper.employeeToDto(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @Valid @RequestBody EmployeeDto employeeDto) {
		
		if(!employeeService.getEmployeesMap().containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		employeeDto.setId(id);
		employeeService.save(employeeMapper.dtoToEmployee(employeeDto));
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}
}
