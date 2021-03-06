package hu.webuni.hr.lacztam.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CompanyDto {
	
	private long companyRegistrationNumber;
	private String name;
	private String address;
	private List<EmployeeDto> employeesList;
	
	public CompanyDto() {
	}
	
	public CompanyDto(long companyRegistrationNumber, String name, String address, List<EmployeeDto> employeesList) {
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

	public List<EmployeeDto> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<EmployeeDto> employeesList) {
		this.employeesList = employeesList;
	}

	@Override
	public String toString() {
		return "CompanyDto [companyRegistrationNumber=" + companyRegistrationNumber + ", name=" + name + ", address="
				+ address + ", employeesList=" + employeesList + ", getCompanyRegistrationNumber()="
				+ getCompanyRegistrationNumber() + ", getName()=" + getName() + ", getAddress()=" + getAddress()
				+ ", getEmployeesList()=" + getEmployeesList() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}	
}
