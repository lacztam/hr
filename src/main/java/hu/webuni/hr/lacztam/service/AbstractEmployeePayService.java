package hu.webuni.hr.lacztam.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;

public abstract class AbstractEmployeePayService implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee update(Employee employee) {
		if(employeeRepository.existsById(employee.getEmployeeId()))
			return null;
		return employeeRepository.save (employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

}
