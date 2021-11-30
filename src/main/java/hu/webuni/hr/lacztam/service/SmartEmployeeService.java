package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MM");

		int workedYear = 0;
		int workedMonth = 0;

		int employeeStartYear = Integer.valueOf(employee.getBeginningOfEmployment().format(yearFormat));
		int employeeStartMonth = Integer.valueOf(employee.getBeginningOfEmployment().format(monthFormat));

		int actualYear = Integer.valueOf(now.format(yearFormat));
		int actualMonth = Integer.valueOf(now.format(monthFormat));

		if (actualYear == employeeStartYear) {
			workedYear = 0;
			if (actualMonth > employeeStartMonth) {
				workedMonth = actualMonth - employeeStartMonth;
			}
		}

		if (actualYear > employeeStartYear) {
			if (actualMonth > employeeStartMonth) {
				workedYear = actualYear - employeeStartYear;
				workedMonth = actualMonth - employeeStartMonth;
			} else if (actualMonth == employeeStartMonth) {
				workedYear = actualYear - employeeStartYear - 1;
				workedMonth = 11;
			} else if (actualMonth < employeeStartMonth) {
				workedYear = actualYear - employeeStartYear - 1;
				if (actualMonth > 1) {
					/*
					 * actualMonth - 1, because the current month not yet done and 13 -
					 * employeeStartMonth, because when you get started working in the month, you'll
					 * complete that month no matter when you started
					 */
					workedMonth = (actualMonth - 1) + (13 - employeeStartMonth);

				} else if (actualMonth == 1) {
					workedMonth = 13 - employeeStartMonth;
				}
			}
		}

		double limit1 = config.getSalary().getSmart().getLimit1();
		double limit2 = config.getSalary().getSmart().getLimit2();
		double limit3 = config.getSalary().getSmart().getLimit3();
		int limit3Year = (int) limit3;

		double limit3Month = 12 * (0.0 + (((limit3 * 10) % 10) / 10)); // how many month

		double percent1 = config.getSalary().getSmart().getPercent1();
		double percent2 = config.getSalary().getSmart().getPercent2();
		double percent3 = config.getSalary().getSmart().getPercent3();

		if (workedYear >= limit1)
			return (int) percent1;
		if (workedYear >= limit2)
			return (int) percent2;
		if (workedYear >= limit3) {

			if (workedYear > limit3Year)
				return (int) percent3;
			if (workedYear == limit3Year && workedMonth >= limit3Month)
				return (int) percent3;
		}
		if (workedYear <= limit3Year && workedMonth < limit3Month)
			return 0;

		return -1;
	}
}
