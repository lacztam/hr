package hu.webuni.hr.lacztam.web;

import java.util.ArrayList;
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
import hu.webuni.hr.lacztam.model.DataWrapper;


@RestController
@RequestMapping("/api/employees")
public class RestEmployeeController {
	
	Map<Long, EmployeeDto> employeesMap = new DataWrapper().getEmployeesMap();
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return new ArrayList<>(employeesMap.values());
	}
	
	@GetMapping(params = "minSalary")
	public List<EmployeeDto> findBySalary(@RequestParam int minSalary){
		return employeesMap.values().stream()
				.filter(e -> e.getMonthlySalary() > minSalary)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto>  getById(@PathVariable long id) {
		EmployeeDto employeeDto = employeesMap.get(id);
		if(employeeDto != null)
			return ResponseEntity.ok(employeeDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto createNewEmployeeDto(@RequestBody EmployeeDto employeeDto) {
		employeesMap.put(employeeDto.getId(), employeeDto);
		return employeeDto;
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if(!employeesMap.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		employeeDto.setId(id);
		employeesMap.put(id, employeeDto);
		return ResponseEntity.ok(employeeDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeesMap.remove(id);
	}
}
