package hu.webuni.hr.lacztam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class HrController {
	
	Map<Long, EmployeeDto> employees = new HashMap();
	{
		employees.put(1l, new EmployeeDto(1l, "Panna", "Adminisztrátor", 1000 ,2021, 11, 20));
		employees.put(2l, new EmployeeDto(2l, "Anna", "PM", 2000, 2020, 10, 12));
		employees.put(3l, new EmployeeDto(3l, "Feri", "Logisztikus", 3000, 2018, 7, 12));
		employees.put(4l, new EmployeeDto(4l, "Tamás", "Architech", 4000, 2014, 8, 22));
		employees.put(5l, new EmployeeDto(5l, "Ági", "Boss", 5000, 2010, 4, 30));
	}
	
	@GetMapping
	public List<EmployeeDto> getAll(){
		return new ArrayList<>(employees.values());
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
	
	// TO-DO: nem mukodik
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
	
	@GetMapping("/salaryover")
	public List<EmployeeDto> salaryOver(@RequestParam String salary){
		if(salary.equals(null) || salary == "") {
			return new ArrayList<>(employees.values());
		}
		
		List<EmployeeDto> salaryEmployees = new ArrayList<>();
		
		for (Map.Entry<Long, EmployeeDto> entry : employees.entrySet()) {

			if(entry.getValue().getMonthlySalary() > Integer.valueOf(salary)) {
				salaryEmployees.add(entry.getValue());
			}	
		}
		return salaryEmployees;
	}
}
