package hu.webuni.hr.lacztam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.lacztam.repository.CompanyRepository;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;

@Service
public class InitDbService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	public void clearDB() {
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}
	
	public void inserTestData() {
		companyRepository.insertEntiresIntoCompany();
		employeeRepository.insertEntiresIntoEmployee();
	}
	
}
