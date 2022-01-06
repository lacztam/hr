package hu.webuni.hr.lacztam.model;

import java.util.List;

public class Company {

	private long companyRegistrationNumber;
	private String name;
	private String address;
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

	@Override
	public String toString() {
		return "Company [companyRegistrationNumber=" + companyRegistrationNumber + ", name=" + name + ", address="
				+ address + ", employeesList=" + employeesList + "]";
	}
}
