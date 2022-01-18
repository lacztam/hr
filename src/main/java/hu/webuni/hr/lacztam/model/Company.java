package hu.webuni.hr.lacztam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private long companyRegistrationNumber;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	private List<Employee> employeesList;
	
	public Company() {
	}
	
	public Company(long companyRegistrationNumber, String name, String address, List<Employee> employeesList) {
		this.companyRegistrationNumber = companyRegistrationNumber;
		this.name = name;
		this.address = address;
		this.employeesList = employeesList;
	}

	public long getCompanyRegistrationNumber() {
		return companyRegistrationNumber;
	}

	public void setCompanyRegistrationNumber(long companyRegistrationNumber) {
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
		this.employeesList = employeesList;
	}
	
	public void addEmployee(Employee employee) {
		if(this.employeesList == null) {
			this.employeesList = new ArrayList<>();
		}
		this.employeesList.add(employee);
		employee.setCompany(this);
	}

	@Override
	public String toString() {
		return "Company [companyRegistrationNumber=" + companyRegistrationNumber + ", name=" + name + ", address="
				+ address + ", employeesList=" + employeesList + "]";
	}
}
