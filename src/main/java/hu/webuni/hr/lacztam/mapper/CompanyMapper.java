package hu.webuni.hr.lacztam.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companiesToDtos(List<Company> companies);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> companies);
	
	List<Company> dtosToCompanies(List<CompanyDto> companies);
	
	CompanyDto companytoDto(Company company);
	
	@Mapping(target = "employeesList", ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);
	
	Company dtoToCompany (CompanyDto companyDto);

	@Mapping(target = "entryDate", source = "beginningOfEmployment")
	@Mapping(target = "company.employeesList", ignore = true)
	@Mapping(target = "title", source = "position.name")
	@Mapping(target = "salary", source = "monthlySalary")
	EmployeeDto employeeToDto(Employee employee);

	@Mapping(target = "beginningOfEmployment", source = "entryDate")
	@Mapping(target = "monthlySalary", source = "salary")
	@Mapping(target = "position.name", source = "title")
	Employee dtoToEmployee(EmployeeDto employeeDto);
}
