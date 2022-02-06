package hu.webuni.hr.lacztam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.PositionDetailsByCompany;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.lacztam.repository.PositionRepository;

@Service
public class SalaryService {

	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	private EmployeeRepository employeeRepository;
		
	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}

	public void setSalaryService(Employee employee) {
		int originalSalary = employee.getMonthlySalary();
		
		employee.setMonthlySalary(
				(int) (employee.getMonthlySalary() * (1.0 + employeeService.getPayRaisePercent(employee) / 100.0)));
		
		int afterSetMonthlySalary = employee.getMonthlySalary();

		if (originalSalary != afterSetMonthlySalary) 
			System.out.println("Salary successfully updated.");
		else 
			System.out.println("The salary has not changed.");
	}
	
	@Transactional
	public void raiseMinSalary(long companyId, String positionName, int minSalary) {
//		positionRepository.findByName(positionName)
//		.forEach(p -> {
//			p.setMinSalary(minSalary);
//			p.getEmployee().forEach(e ->{
//				if(e.getMonthlySalary() < minSalary)
//					e.setMonthlySalary(minSalary);
//			});
//		});
		List<PositionDetailsByCompany> positionDetails = positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, companyId);
		positionDetails.forEach(pd -> pd.setMinSalary(minSalary));
		positionDetailsByCompanyRepository.updateSalaries(companyId, positionName, minSalary);
	}
}
