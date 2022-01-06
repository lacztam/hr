package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.Employee;

@Service
public class CompanyService {
	private Map<Long, Company> companiesMap = new HashMap<Long, Company>();
	
	public CompanyService() {
		this.companiesMap = initCompaniesMap();
	}
	
	private Map<Long, Company> initCompaniesMap(){
		Employee e1 =  new Employee(1l, "Panna", "Adminisztrátor", 1000 ,LocalDateTime.of(2010, 7, 30, 12, 14));
		Employee e2 =  new Employee(2l, "Anna", "PM", 2000, LocalDateTime.of(2012, 10, 12, 10, 10));
		Employee e3 =  new Employee(4l, "Tamás", "Architech", 4000, LocalDateTime.of(2014, 8, 22, 15, 15));
		Employee e4 =  new Employee(5l, "Ági", "Boss", 5000, LocalDateTime.of(2010, 4, 30, 5, 30));
		
		List<Employee> employeesList1 = new ArrayList<Employee>();
		List<Employee> employeesList2 = new ArrayList<Employee>();
		
		employeesList1.add(e1);
		employeesList1.add(e2);
		employeesList2.add(e3);
		employeesList2.add(e4);
		
		Company company1 = new Company(1,"Company1","Some Street 10", employeesList1);
		Company company2 = new Company(2,"Company2","That Street 20", employeesList2);
		
		companiesMap.put(company1.getCompanyRegistrationNumber(), company1);
		companiesMap.put(company2.getCompanyRegistrationNumber(), company2);
		
		return companiesMap;
	}
	
	public List<Employee> employeesOfCompany(Company company){
		return company.getEmployeesList();
	}

	public void removeEmployee(Company company, Employee employee) {
		company.getEmployeesList().remove(employee);
	}
	
	public void delete(long id) {
		companiesMap.remove(id);
	}
	
	public Company save(Company company) {
		return companiesMap.put(company.getCompanyRegistrationNumber(), company);
	}
	
	public Map<Long, Company> getCompaniesMap() {
		return companiesMap;
	}

	public void setCompaniesMap(Map<Long, Company> companiesMap) {
		this.companiesMap = companiesMap;
	}

	public Company findById(long companyId) {
		
		return companiesMap.get(companyId);
	}
	
	public List<Company> findAll(){
		return new ArrayList<>(companiesMap.values());
	}
}
