package hu.webuni.hr.lacztam.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hu.webuni.hr.lacztam.dto.EmployeeDto;


@RestController
@RequestMapping("/api/employees")
public class RestEmployeeController {
	
	Map<Long, EmployeeDto> employees = new HashMap();
	{
		employees.put(1l, new EmployeeDto(1l, "Panna", "Adminisztrátor", 1000 ,LocalDateTime.of(2010, 7, 30, 12, 14)));
		employees.put(2l, new EmployeeDto(2l, "Anna", "PM", 2000, LocalDateTime.of(2012, 10, 12, 10, 10)));
		employees.put(3l, new EmployeeDto(4l, "Tamás", "Architech", 4000, LocalDateTime.of(2014, 8, 22, 15, 15)));
		employees.put(4l, new EmployeeDto(5l, "Ági", "Boss", 5000, LocalDateTime.of(2010, 4, 30, 5, 30)));
		employees.put(5l, new EmployeeDto(5l, "Ági", "Boss", 5000, LocalDateTime.of(2010, 4, 30, 10, 10)));
	}
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return new ArrayList<>(employees.values());
	}
	
	@GetMapping(params = "minSalary")
	public List<EmployeeDto> findBySalary(@RequestParam int minSalary){
		return employees.values().stream()
				.filter(e -> e.getMonthlySalary() > minSalary)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto>  getById(@PathVariable long id) {
		EmployeeDto employeeDto = employees.get(id);
		if(employeeDto != null)
			return ResponseEntity.ok(employeeDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto createNewEmployeeDto(@RequestBody EmployeeDto employeeDto) {
		employees.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if(!employees.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		employeeDto.setId(id);
		employees.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employees.remove(id);
	}
}
