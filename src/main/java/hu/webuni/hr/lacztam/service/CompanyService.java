package hu.webuni.hr.lacztam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.mapper.EmployeeMapper;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.Position;
import hu.webuni.hr.lacztam.model.Qualification;
import hu.webuni.hr.lacztam.repository.CompanyRepository;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.PositionRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	public Company update(Company company) {
		if(!companyRepository.existsById(company.getId()))
			return null;
		return companyRepository.save(company);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}

	public void delete(long id) {
		companyRepository.deleteById(id);
	}
	
	public Company addEmployee(long companyId, EmployeeDto employeeDto) {
		Company company = companyRepository.findById(companyId).get();
		
		if(company == null) 
			return null;
		
		Employee employee = employeeMapper.dtoToEmployee(employeeDto);
		
		List<Position> empPosition = positionRepository.findByName(employeeDto.getTitle());
		
		if(empPosition.isEmpty() || empPosition == null) {
			Position position = positionRepository.save(new Position(employee.getPosition().getName(), Qualification.COLLEGE, employee.getMonthlySalary()));
			
			employee.setPosition(position);
		}else {
			employee.setPosition(empPosition.get(0));
		}
		
		company.addEmployee(employee);
		companyRepository.save(company);
		
		return company;
	}
	
	public Company deleteEmployee(long companyId, long employeeId) {
		Company company = companyRepository.findById(companyId).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(company);
		company.getEmployeesList().remove(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	public Company replaceEmployees(long companyId, List<Employee> employees) {
		Company company = companyRepository.findById(companyId).get();
		company.getEmployeesList().forEach(e->e.setCompany(null));
		company.getEmployeesList().clear();
		
		for(Employee emp: employees) {
			company.addEmployee(emp);
			employeeRepository.save(emp);
		}
		companyRepository.save(company);
		return company;
	}
	
}
