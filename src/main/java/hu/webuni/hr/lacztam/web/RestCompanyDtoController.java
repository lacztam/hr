package hu.webuni.hr.lacztam.web;

import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
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
import hu.webuni.hr.lacztam.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class RestCompanyDtoController {

	private CompanyService companyService;
	private CompanyMapper companyMapper;
	private EmployeeMapper employeeMapper;
	
	
	public RestCompanyDtoController(CompanyService companyService, CompanyMapper companyMapper,
			EmployeeMapper employeeMapper) {
		super();
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.employeeMapper = employeeMapper;
	}

	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false) Boolean full){
		List<Company> employees = companyService.findAll();
		
		if(isFull(full)) {
			return companyMapper.companiesToDtos(employees);
		} else {
			return companyMapper.companiesToSummaryDtos(employees);
		}
	}
	
	private boolean isFull(Boolean full) {
		return full != null && full;
	}
	
	private Company findByIdOrThrow(long id) {
		return companyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		 
	}
	
	@GetMapping("/{companyId}")
	public CompanyDto getCompany(@PathVariable long companyId) {
		Company company = findByIdOrThrow(companyId);
		
		if(company != null)
			return companyMapper.companytoDto(company);
		else
			return companyMapper.companyToSummaryDto(company);
	}
	
	@PostMapping
	public CompanyDto createNewCompany(@RequestBody CompanyDto companyDto) {
		return companyMapper.companytoDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}
	
	@PutMapping("/{companyId}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long companyId, @Valid @RequestBody CompanyDto companyDto){
		companyDto.setCompanyRegistrationNumber(companyId);
		Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
		if(updatedCompany == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(companyDto);
	}
	
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable long companyId) {
		companyService.delete(companyId);
	}
	
	@PostMapping("/{companyId}/addEmployee")
	public CompanyDto addEmployeeToCompany(@PathVariable long companyId, @Valid @RequestBody EmployeeDto employeeDto) {
			try {
				return companyMapper.companytoDto(companyService.addEmployee(companyId, employeeMapper.dtoToEmployee(employeeDto)));
			}catch (NoSuchElementException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
	}
	
	@DeleteMapping("/{companyId}/deleteEmployee/{employeeId}")
	public CompanyDto deleteEmployeeFromCompany(@PathVariable long companyId, @PathVariable long employeeId) {
		try {
			return companyMapper.companytoDto(companyService.deleteEmployee(companyId, employeeId));
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
//	@PostMapping("/{companyId}/changeEmployeeList")
//	public CompanyDto changeEmployeeList(@PathVariable long companyId,
//										@RequestBody List<EmployeeDto> employeeList) {
//		
//		Company company = findByIdOrThrow(companyId);
//		company.setEmployeesList(employeeMapper.dtosToEmployees(employeeList));
//		
//		return companyMapper.companytoDto(company);
//	}
	
	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees) {
		try {
			return companyMapper.companytoDto(companyService.replaceEmployees(companyId, employeeMapper.dtosToEmployees(newEmployees)));
		} catch(NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}