package hu.webuni.hr.lacztam.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Component
public class DataWrapper {
	private Map<Long, EmployeeDto> employeesMap = new HashMap<Long, EmployeeDto>();
	private Map<Long, CompanyDto> companiesMap = new HashMap<Long, CompanyDto>();
	
	public DataWrapper() {
		employeesMap = getEmployeesMap();
		companiesMap = getCompaniesMap();
	}
	
	public Map<Long, EmployeeDto> getEmployeesMap() {
		LocalDateTime ldt1 = LocalDateTime.of(2021, 11, 20, 0, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 12, 0, 0);
		LocalDateTime ldt3 = LocalDateTime.of(2018, 7, 1, 0, 0);
		LocalDateTime ldt4 = LocalDateTime.of(2014, 8, 22, 0, 0);
		LocalDateTime ldt5 = LocalDateTime.of(2010, 4, 17, 0, 0);
		
		EmployeeDto e1 = new EmployeeDto(1l, "Panna", "Adminisztrátor", 1000 ,ldt1);
		EmployeeDto e2 = new EmployeeDto(2l, "Kanna", "PM", 1500 ,ldt1);
		EmployeeDto e3 = new EmployeeDto(3l, "Anna", "PM", 2000, ldt2);
		EmployeeDto e4 = new EmployeeDto(4l, "Feri", "Logisztikus", 3000, ldt3);
		EmployeeDto e5 = new EmployeeDto(5l, "Tamás", "Architech", 4000, ldt4);
		EmployeeDto e6 = new EmployeeDto(6l, "Ági", "Boss", 5000, ldt5);
		EmployeeDto e7 = new EmployeeDto(7L, "Name", "Title", 4000, LocalDateTime.of(2013, 1, 1, 10, 10, 0));
		
		employeesMap.put(e1.getId(), e1);
		employeesMap.put(e2.getId(), e2);
		employeesMap.put(e3.getId(), e3);
		employeesMap.put(e4.getId(), e4);
		employeesMap.put(e5.getId(), e5);
		employeesMap.put(e6.getId(), e6);
		employeesMap.put(e7.getId(), e7);
		return employeesMap;
	}

	public Map<Long, CompanyDto> getCompaniesMap(){
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
}
