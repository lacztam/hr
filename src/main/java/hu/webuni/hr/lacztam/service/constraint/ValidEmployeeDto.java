package hu.webuni.hr.lacztam.service.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidEmployeeDtoValidator.class)
@Target({ CONSTRUCTOR, METHOD, FIELD, PARAMETER, TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidEmployeeDto {
	
	String message() default "Error while validating employeeDto, name or title or salary or employee start date is invalid.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
