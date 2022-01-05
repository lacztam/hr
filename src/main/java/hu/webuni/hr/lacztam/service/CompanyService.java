package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Service
public class CompanyService {
	private Map<Long, CompanyDto> companiesMap = new HashMap<Long, CompanyDto>();
	
	public CompanyService() {
		this.companiesMap = initCompaniesMap();
	}
	
	private Map<Long, CompanyDto> initCompaniesMap(){
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
		
		CompanyDto company1 = new CompanyDto(1,"Company1","Some Street 10", employeesList1);
		CompanyDto company2 = new CompanyDto(2,"Company2","That Street 20", employeesList2);
		
		companiesMap.put(company1.getCompanyRegistrationNumber(), company1);
		companiesMap.put(company2.getCompanyRegistrationNumber(), company2);
		
		return companiesMap;
	}

	public Map<Long, CompanyDto> getCompaniesMap() {
		return companiesMap;
	}

	public void setCompaniesMap(Map<Long, CompanyDto> companiesMap) {
		this.companiesMap = companiesMap;
	}

	@Override
	public String toString() {
		return "CompanyService.toString():\n[companiesMap=" + companiesMap + "]";
	}
}
