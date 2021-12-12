package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.config.HrConfigProperties.Smart;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Service
public class SmartEmployeeService implements EmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(EmployeeDto employee) {

		double yearsWorked = ChronoUnit.DAYS.between(employee.getBeginningOfEmployment(), LocalDateTime.now()) / 365.0;
		Smart smartConfig = config.getSalary().getSmart();
		
		TreeMap<Double, Integer> raisingIntervals = smartConfig.getLimits();
		
		Entry<Double, Integer> floorEntry = raisingIntervals.floorEntry(yearsWorked);
		return floorEntry == null ? 0 : floorEntry.getValue();
	}
}
