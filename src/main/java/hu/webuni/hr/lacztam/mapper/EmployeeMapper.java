package hu.webuni.hr.lacztam.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.hr.lacztam.dto.CompanyDto;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Company;
import hu.webuni.hr.lacztam.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
	
	@Mapping(target = "id", source = "employeeId")
	@Mapping(target = "entryDate", source = "beginningOfEmployment")
	@Mapping(target = "company.employeesList", ignore = true)
	@Mapping(target = "title", source = "position.name")
	@Mapping(target = "salary", source = "monthlySalary")
	EmployeeDto employeeToDto(Employee employee);

	@Mapping(target = "employeeId", source = "id")	
	@Mapping(target = "beginningOfEmployment", source = "entryDate")
	@Mapping(target = "monthlySalary", source = "salary")
	@Mapping(target = "position.name", source = "title")
	Employee dtoToEmployee(EmployeeDto employeeDto);
}