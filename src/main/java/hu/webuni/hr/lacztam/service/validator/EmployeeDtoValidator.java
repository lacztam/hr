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
		
		// NotReadablePropertyException kivételeket dob, ez így a jó megoldás?
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeDto.getTitle()", "Error, employeeDto title value is null or empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeDto.getName()", "Error, employeeDto name value is null or empty.");
		
		if(now.isBefore(employeeDto.getBeginningOfEmployment()))
			errors.rejectValue(String.valueOf(employeeDto.getBeginningOfEmployment()), "Error, the employee start date must be past.");
			
		if(employeeDto.getMonthlySalary() < 0)
			errors.rejectValue(String.valueOf(employeeDto.getMonthlySalary()), "Error, the salary cannot be negative value.");
	}

}
