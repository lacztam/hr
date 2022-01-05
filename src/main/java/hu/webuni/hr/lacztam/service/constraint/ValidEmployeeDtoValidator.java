package hu.webuni.hr.lacztam.service.constraint;

import java.time.LocalDateTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Component
public class ValidEmployeeDtoValidator 
	implements ConstraintValidator<ValidEmployeeDto, EmployeeDto>{
	
	public void initialize(EmployeeDto employeeDto) {
	}
	
	@Override
	public boolean isValid(EmployeeDto employeeDto, ConstraintValidatorContext context) {
		boolean isValid = false;
		
		LocalDateTime now = LocalDateTime.now();
		
//		Így kiírja a hibát.
		if(employeeDto.getName() == "" || employeeDto.getName() == null)
			throw new IllegalArgumentException("EmployeeDto name value is null or empty.");
		
		if(employeeDto.getTitle() == null || employeeDto.getTitle() == "")
			throw new IllegalArgumentException("EmployeeDto title value is null or empty.");
		
		if(now.isBefore(employeeDto.getBeginningOfEmployment()))
			throw new IllegalArgumentException("EmployeeDto start date is invalid (must be past): " 
												+ employeeDto.getBeginningOfEmployment());
		if(employeeDto.getMonthlySalary() < 0)
			throw new IllegalArgumentException("EmployeeDto salary is invalid (must be positive): " 
												+ employeeDto.getMonthlySalary());		
			
		isValid = true;
		
		return isValid;
	}

}
