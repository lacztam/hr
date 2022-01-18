package hu.webuni.hr.lacztam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeePayService{

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

}