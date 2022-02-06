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
import hu.webuni.hr.lacztam.model.Position;
import hu.webuni.hr.lacztam.model.Qualification;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.PositionRepository;
import hu.webuni.hr.lacztam.service.EmployeeService;
import hu.webuni.hr.lacztam.service.validator.EmployeeDtoValidator;

@RestController
@RequestMapping("/api/employees")
public class RestEmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalay){
		List<Employee> employees = null;
		if(minSalay == null) {
			employees = employeeService.findAll();
		} else {
			employees = employeeRepository.findByMonthlySalaryGreaterThan(minSalay);
		}
		return employeeMapper.employeesToDtos(employeeService.findAll());
	}
	
	@GetMapping(params = "minSalary")
	public List<EmployeeDto> findBySalary(@RequestParam int minSalary){
		return employeeMapper.employeesToDtos(employeeRepository.findAll().stream()
				.filter(e -> e.getMonthlySalary() > minSalary)
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public EmployeeDto getById(@PathVariable long id) {
		
		Employee employee = employeeRepository.findById(id).orElseThrow();
		
		if(employee != null)
			return employeeMapper.employeeToDto(employee);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public EmployeeDto createNewEmployeeDto(@Valid @RequestBody EmployeeDto employeeDto, BindingResult result) {
		List<Position> pos = positionRepository.findByName(employeeDto.getTitle());
		Position position = null;
		if(pos.isEmpty() || pos == null) {
			position = positionRepository.save(new Position(employeeDto.getTitle(), Qualification.COLLEGE, employeeDto.getSalary()));
		}else {
			
		}
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		employee.setPosition(position);
				
		if(result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		
		return employeeMapper.employeeToDto(employeeRepository.save(employee));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @Valid @RequestBody EmployeeDto employeeDto) {
		
		if(employeeRepository.findById(id).equals(null)) {
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
	
	@GetMapping("/map")
	public ResponseEntity<Employee> mapDtoToEmployee(@RequestBody EmployeeDto employeeDto) {
		List<Position> pos = positionRepository.findByName(employeeDto.getTitle());
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		if(pos.isEmpty() || pos == null)
			employee.setPosition(positionRepository.save(new Position(employeeDto.getTitle(),Qualification.COLLEGE,employeeDto.getSalary())));
		employeeRepository.save(employee);	
		return ResponseEntity.ok(employee);
	}
	
}
