package hu.webuni.hr.lacztam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Service
public class DefaultEmployeeService extends EmployeePayService{

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(EmployeeDto employee) {
		return config.getSalary().getDef().getPercent();
	}
}
