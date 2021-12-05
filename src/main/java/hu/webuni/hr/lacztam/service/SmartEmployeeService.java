package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lacztam.config.HrConfigProperties;
import hu.webuni.hr.lacztam.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

	@Autowired
	HrConfigProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		LocalDateTime beginningOfEmployment = employee.getBeginningOfEmployment();
		LocalDateTime now = LocalDateTime.now();
		int startDay = beginningOfEmployment.getDayOfMonth();
		int currentDay = now.getDayOfMonth();
		
		long monthsBetweenBeginningOfWorkAndNow = ChronoUnit.MONTHS.between(beginningOfEmployment, now);
		
		// because every started month will be a completed worked month
		// despite the fact, that its not started from the first day of month
		if(startDay > currentDay) 
			monthsBetweenBeginningOfWorkAndNow  +=1;
		
		int monthsValueOfLimit1 = (int) (12 * config.getSalary().getSmart().getLimit1());
		int monthsValueOfLimit2 = (int) (12 * config.getSalary().getSmart().getLimit2());
		int monthsValueOfLimit3 = (int) (12 * config.getSalary().getSmart().getLimit3());

		int percent1 = (int) config.getSalary().getSmart().getPercent1();
		int percent2 = (int) config.getSalary().getSmart().getPercent2();
		int percent3 = (int) config.getSalary().getSmart().getPercent3();

		if (monthsBetweenBeginningOfWorkAndNow >= monthsValueOfLimit1)
			return percent1;
		if (monthsBetweenBeginningOfWorkAndNow >= monthsValueOfLimit2)
			return percent2;
		if (monthsBetweenBeginningOfWorkAndNow >= monthsValueOfLimit3)
			return percent3;
		if (monthsBetweenBeginningOfWorkAndNow < monthsValueOfLimit3)
			return 0;

		return -1;
	}
}
