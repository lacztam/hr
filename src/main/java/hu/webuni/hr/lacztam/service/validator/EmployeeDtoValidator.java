 	package hu.webuni.hr.lacztam.service.validator;

import java.time.LocalDateTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Component
public class EmployeeDtoValidator 
	implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EmployeeDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeDto employeeDto = (EmployeeDto) target;
		LocalDateTime now = LocalDateTime.now();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeDto.getTitle()", "Error, employeeDto title value is null or empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeDto.getName()", "Error, employeeDto name value is null or empty.");
		
		if(now.isBefore(employeeDto.getEntryDate()))
			errors.rejectValue(String.valueOf(employeeDto.getEntryDate()), "Error, the employee start date must be past.");
			
		if(employeeDto.getSalary() < 0)
			errors.rejectValue(String.valueOf(employeeDto.getSalary()), "Error, the salary cannot be negative value.");
	}

}
