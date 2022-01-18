package hu.webuni.hr.lacztam.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import hu.webuni.hr.lacztam.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO company(\r\n"
			+ "	company_registration_number, address, name)\r\n"
			+ "VALUES \r\n"
			+ "	(1, '1111, Szeged, Okos utca 12345', 'TCompany1'),\r\n"
			+ "	(2, '2222, Budapest, Tudós körút 2', 'GCompany2'),\r\n"
			+ "	(3, '3333, Székesfehérvár, Szép utca 3', 'HCompany3');",
				nativeQuery = true)
	public void insertEntiresIntoCompany();
	
	@Query(value = "SELECT c.company_registration_number, c.address, c.name\r\n"
					+ "FROM COMPANY c, EMPLOYEE e\r\n"
					+ "WHERE c.company_registration_number = e.company_company_registration_number\r\n"
					+ "AND e.monthly_salary > :minSalary \r\n"
					+ "GROUP BY c.company_registration_number, c.address, c.name;",
						nativeQuery = true)
	public List<Company> companiesThatHaveEmployeesMonthlySalaryGreaterThan(@Param("minSalary") Integer minSalary);
}
