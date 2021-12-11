package hu.webuni.hr.lacztam.web;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Employee;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyDtoController {

	Map<Long, CompanyDto> companiesMap = new HashMap<Long, CompanyDto>();
	{
		EmployeeDto e1 =  new EmployeeDto(1l, "Panna", "Adminisztrátor", 1000 ,LocalDateTime.of(2010, 7, 30, 12, 14));
		EmployeeDto e2 =  new EmployeeDto(2l, "Anna", "PM", 2000, LocalDateTime.of(2012, 10, 12, 10, 10));
		EmployeeDto e3 =  new EmployeeDto(4l, "Tamás", "Architech", 4000, LocalDateTime.of(2014, 8, 22, 15, 15));
		EmployeeDto e4 =  new EmployeeDto(5l, "Ági", "Boss", 5000, LocalDateTime.of(2010, 4, 30, 5, 30));
		List<EmployeeDto> employeesList1 = new ArrayList<EmployeeDto>();
		List<EmployeeDto> employeesList2 = new ArrayList<EmployeeDto>();
		
		employeesList1.add(e1);
		employeesList1.add(e2);
		
		employeesList2.add(e3);
		employeesList2.add(e4);
		
		CompanyDto company1 = new CompanyDto(1234,"Company1","Some Street 10", employeesList1);
		CompanyDto company2 = new CompanyDto(5678,"Company2","That Street 20", employeesList2);
		
		companiesMap.put(company1.getCompanyRegistrationNumber(), company1);
		companiesMap.put(company2.getCompanyRegistrationNumber(), company2);
	}
	

	@GetMapping
	public List<CompanyDto> getAllCompanies(){
		System.out.println(companiesMap.get(1234L));

		return new ArrayList<>(companiesMap.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompany(@PathVariable long id) {
		CompanyDto companyDto = companiesMap.get(id);
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
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(
			@PathVariable long id,
			@RequestBody CompanyDto companyDto){
		
		if(!companiesMap.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		companyDto.setCompanyRegistrationNumber(id);
		companiesMap.put(id, companyDto);
		
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companiesMap.remove(id);
	}
	
	
}
