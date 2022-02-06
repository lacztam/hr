package hu.webuni.hr.lacztam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private Long id;
	private int companyRegistrationNumber;
	private String name;
	private String address;
	private CompanyType companyType;
	
	@OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
	private List<Employee> employeesList;
	
	public Company() {
	}
	
	public Company(Long id, int companyRegistrationNumber, String name, String address, CompanyType companyType,
			List<Employee> employeesList) {
		this.id = id;
		this.companyRegistrationNumber = companyRegistrationNumber;
		this.name = name;
		this.address = address;
		this.companyType = companyType;
		this.employeesList = employeesList;
	}

	public long getCompanyRegistrationNumber() {
		return companyRegistrationNumber;
	}

	public void setCompanyRegistrationNumber(int companyRegistrationNumber) {
		this.companyRegistrationNumber = companyRegistrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<Employee> employeesList) {
		if(this.employeesList.isEmpty() || this.employeesList == null)
			this.employeesList = new ArrayList<>(employeesList);
		this.employeesList = new ArrayList<>(employeesList);
	}
	
	public void addEmployee(Employee employee) {
		if(this.employeesList == null) {
			this.employeesList = new ArrayList<>();
		}
		this.employeesList.add(employee);
		employee.setCompany(this);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
