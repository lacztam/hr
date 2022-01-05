package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import hu.webuni.hr.lacztam.dto.EmployeeDto;

@Service
public class EmployeeService {
	private Map<Long, EmployeeDto> employeesMap = new HashMap<>();
	
	public EmployeeService() {
		employeesMap = initEmployeesMap();
	}
	
	private Map<Long, EmployeeDto> initEmployeesMap() {
		LocalDateTime ldt1 = LocalDateTime.of(2021, 11, 20, 0, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 12, 0, 0);
		LocalDateTime ldt3 = LocalDateTime.of(2018, 7, 1, 0, 0);
		LocalDateTime ldt4 = LocalDateTime.of(2014, 8, 22, 0, 0);
		LocalDateTime ldt5 = LocalDateTime.of(2010, 4, 17, 0, 0);
		
		EmployeeDto e1 = new EmployeeDto(1l, "Panna", "Adminisztrátor", 1000 ,ldt1);
		EmployeeDto e2 = new EmployeeDto(2l, "Kanna", "PM", 1500 ,ldt1);
		EmployeeDto e3 = new EmployeeDto(3l, "Anna", "PM", 2000, ldt2);
		EmployeeDto e4 = new EmployeeDto(4l, "Feri", "Logisztikus", 3000, ldt3);
		EmployeeDto e5 = new EmployeeDto(5l, "Tamás", "Architech", 4000, ldt4);
		EmployeeDto e6 = new EmployeeDto(6l, "Ági", "Boss", 5000, ldt5);
		EmployeeDto e7 = new EmployeeDto(7L, "Name", "Title", 4000, LocalDateTime.of(2013, 1, 1, 10, 10, 0));
		
		employeesMap.put(e1.getId(), e1);
		employeesMap.put(e2.getId(), e2);
		employeesMap.put(e3.getId(), e3);
		employeesMap.put(e4.getId(), e4);
		employeesMap.put(e5.getId(), e5);
		employeesMap.put(e6.getId(), e6);
		employeesMap.put(e7.getId(), e7);
		return employeesMap;
	}

	public Map<Long, EmployeeDto> getEmployeesMap() {
		return employeesMap;
	}

	public void setEmployeesMap(Map<Long, EmployeeDto> employeesMap) {
		this.employeesMap = employeesMap;
	}

	@Override
	public String toString() {
		return "EmployeeService.toString():\n[employeesMap=" + employeesMap + "]";
	}
}
