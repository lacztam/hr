package hu.webuni.hr.lacztam.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.webuni.hr.lacztam.model.Employee;

@Service
public class EmployeeService {
	private Map<Long, Employee> employeesMap = new HashMap<>();
	
	public EmployeeService() {
		employeesMap = initEmployeesMap();
	}
	
	private Map<Long, Employee> initEmployeesMap() {
		LocalDateTime ldt1 = LocalDateTime.of(2021, 11, 20, 0, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2020, 10, 12, 0, 0);
		LocalDateTime ldt3 = LocalDateTime.of(2018, 7, 1, 0, 0);
		LocalDateTime ldt4 = LocalDateTime.of(2014, 8, 22, 0, 0);
		LocalDateTime ldt5 = LocalDateTime.of(2010, 4, 17, 0, 0);
		
		Employee e1 = new Employee(1l, "Panna", "Adminisztrátor", 1000 ,ldt1);
		Employee e2 = new Employee(2l, "Kanna", "PM", 1500 ,ldt1);
		Employee e3 = new Employee(3l, "Anna", "PM", 2000, ldt2);
		Employee e4 = new Employee(4l, "Feri", "Logisztikus", 3000, ldt3);
		Employee e5 = new Employee(5l, "Tamás", "Architech", 4000, ldt4);
		Employee e6 = new Employee(6l, "Ági", "Boss", 5000, ldt5);
		Employee e7 = new Employee(7L, "Name", "Title", 4000, LocalDateTime.of(2013, 1, 1, 10, 10, 0));
		
		employeesMap.put(e1.getId(), e1);
		employeesMap.put(e2.getId(), e2);
		employeesMap.put(e3.getId(), e3);
		employeesMap.put(e4.getId(), e4);
		employeesMap.put(e5.getId(), e5);
		employeesMap.put(e6.getId(), e6);
		employeesMap.put(e7.getId(), e7);
		return employeesMap;
	}

	public Employee save(Employee employee) {
		employeesMap.put(employee.getId(), employee);
		return employee;
	}
	
	public List<Employee> findAll(){
		return new ArrayList<>(employeesMap.values());
	}
	
	public Employee findById(long id) {
		return employeesMap.get(id);
	}
	
	public void delete(long id) {
		employeesMap.remove(id);
	}
	
	public Map<Long, Employee> getEmployeesMap() {
		return employeesMap;
	}

	public void setEmployeesMap(Map<Long, Employee> employeesMap) {
		this.employeesMap = employeesMap;
	}

	@Override
	public String toString() {
		return "EmployeeService.toString():\n[employeesMap=" + employeesMap + "]";
	}
}
