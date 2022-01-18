package hu.webuni.hr.lacztam.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
	
	@Mapping(target = "salary", source = "monthlySalary")
	@Mapping(target = "entryDate", source = "beginningOfEmployment")
	@Mapping(target = "company.employeesList", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);
}