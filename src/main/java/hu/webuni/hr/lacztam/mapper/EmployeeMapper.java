package hu.webuni.hr.lacztam.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	
	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	List<Employee> DtosToEmployees(List<EmployeeDto> employees);
	
	EmployeeDto employeeToDto(Employee employee);

	Employee dtoToEmployee(EmployeeDto employeeDto);
}