package hu.webuni.hr.lacztam.web;

import java.util.ArrayList;
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
import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.DataWrapper;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyDtoController {

	Map<Long, CompanyDto> companiesMap = new DataWrapper().getCompaniesMap();

	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false) String full){
		
//		if(full == null || full == "false" || full = "") {
//			List<CompanyDto> withoutEmployees = List.copyOf(companiesMap.values());
//			for(int i=0; i<withoutEmployees.size(); i++) {
//				withoutEmployees.get(i).setEmployeesList(null);
//			}
//			return withoutEmployees;
//		}

		return new ArrayList<>(companiesMap.values());
	}
	
	@GetMapping("/{companyId}")
	public ResponseEntity<CompanyDto> getCompany(@PathVariable long companyId) {
		CompanyDto companyDto = companiesMap.get(companyId);
		if(companyDto != null)
			return ResponseEntity.ok(companyDto);
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public CompanyDto createNewCompany(@RequestBody CompanyDto companyDto) {
		companiesMap.put(companyDto.getCompanyRegistrationNumber(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{companyId}")
	public ResponseEntity<CompanyDto> modifyCompany(
			@PathVariable long companyId,
			@RequestBody CompanyDto companyDto){
		
		if(!companiesMap.containsKey(companyId)) {
			return ResponseEntity.notFound().build();
		}
		companyDto.setCompanyRegistrationNumber(companyId);
		companiesMap.put(companyId, companyDto);
		
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable long companyId) {
		companiesMap.remove(companyId);
	}
	
	@PostMapping("/{companyId}/addEmployee")
	public CompanyDto addEmployeeToCompany(
					@PathVariable long companyId,
					@RequestBody EmployeeDto employeeDto) {
			CompanyDto companyDto = companiesMap.get(companyId);
			companyDto.getEmployeesList().add(employeeDto);
			
		return companyDto;
	}
	
	@DeleteMapping("/{companyId}/deleteEmployee/{employeeId}")
	public void deleteEmployeeFromCompany(
			@PathVariable long companyId,
			@PathVariable long employeeId) {
		CompanyDto companyDto = companiesMap.get(companyId);
		
		EmployeeDto removeEmployee = 
				companyDto.getEmployeesList().stream().filter(employee -> employee.getId() == employeeId).findAny().orElse(null);
		
		companyDto.getEmployeesList().remove(removeEmployee);
	}
	
	@PostMapping("/{companyId}/changeEmployeeList")
	public CompanyDto changeEmployeeList(@PathVariable long companyId,
										@RequestBody List<EmployeeDto> employeeList) {
		
		CompanyDto companyDto = companiesMap.get(companyId);
		companyDto.getEmployeesList().clear();
		companyDto.setEmployeesList(employeeList);
		
		return companyDto;
	}
}
