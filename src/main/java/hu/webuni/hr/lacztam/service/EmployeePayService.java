package hu.webuni.hr.lacztam.service;

import hu.webuni.hr.lacztam.dto.EmployeeDto;

public abstract class EmployeePayService {
	public abstract int getPayRaisePercent(EmployeeDto employeeDto);
}
