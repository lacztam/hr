package hu.webuni.hr.lacztam.web;

import java.util.ArrayList;
import java.util.List;

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

import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.mapper.CompanyMapper;
import hu.webuni.hr.lacztam.mapper.EmployeeMapper;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyDtoController {

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false) String full){
		return new ArrayList<CompanyDto>(companyMapper.companiesToDtos(companyService.findAll()));
	}
	
	@GetMapping("/{companyId}")
	public CompanyDto getCompany(@PathVariable long companyId) {
		Company company = companyService.findById(companyId);
		
		if(company != null)
			return companyMapper.companytoDto(company);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public CompanyDto createNewCompany(@Valid @RequestBody CompanyDto companyDto, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));
		
		return companyMapper.companytoDto(company);
	}
	
	@PutMapping("/{companyId}")
	public ResponseEntity<CompanyDto> modifyCompany(
			@PathVariable long companyId,
			@Valid @RequestBody CompanyDto companyDto){
		
		if(!companyService.getCompaniesMap().containsKey(companyId)) {
			return ResponseEntity.notFound().build();
		}
		
		companyService.save(companyMapper.dtoToCompany(companyDto));
		
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable long companyId) {
		companyService.delete(companyId);
	}
	
	@PostMapping("/{companyId}/addEmployee")
	public CompanyDto addEmployeeToCompany(
					@PathVariable long companyId,
					@Valid @RequestBody EmployeeDto employeeDto) {
			Company company = companyService.findById(companyId);
			company.getEmployeesList().add(employeeMapper.dtoToEmployee(employeeDto));
						
		return companyMapper.companytoDto(company);
	}
	
	@DeleteMapping("/{companyId}/deleteEmployee/{employeeId}")
	public void deleteEmployeeFromCompany(
			@PathVariable long companyId,
			@PathVariable long employeeId) {
		Company company = companyService.findById(companyId);
		
		Employee removeEmployee = 
				company.getEmployeesList().stream()
										.filter(employee -> employee.getId() == employeeId)
										.findAny().orElse(null);
		
		companyService.removeEmployee(company, removeEmployee);
	}
	
	@PostMapping("/{companyId}/changeEmployeeList")
	public CompanyDto changeEmployeeList(@PathVariable long companyId,
										@RequestBody List<EmployeeDto> employeeList) {
		
		Company company = companyService.findById(companyId);
		company.setEmployeesList(employeeMapper.DtosToEmployees(employeeList));
		
		return companyMapper.companytoDto(company);
	}
}
