package hu.webuni.hr.lacztam.web;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.DataWrapper;
import hu.webuni.hr.lacztam.service.EmployeeService;

@RestController
@RequestMapping("/api/service")
public class RestServiceController {

	@Autowired
	DataWrapper dataWrapper;
	
	Map<Long, EmployeeDto> employeesMap = new DataWrapper().getEmployeesMap();

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public int getDiscountPercent(@RequestBody EmployeeDto employeeDto) {
		return employeeService.getPayRaisePercent(employeeDto);
	}
	
	@GetMapping("/{employeeId}")
	public EmployeeDto getJSONformatOfEmloyee(@PathVariable long employeeId) {
		
		return employeesMap.get(employeeId);
	}	
}
