package hu.webuni.hr.lacztam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.lacztam.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long>{
	List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long companyId);
	
	
//	@Query("UPDATE Employee e "
//			+ "SET e.monthlySalary = :minSalary "
//			+ "WHERE e.position.name = :position "
//			+ "AND e.company.id = :companyId "
//			+ "AND e.monthlySalary < :minSalary")
	
	@Modifying
	@Query("UPDATE Employee e "
			+ "SET e.monthlySalary = :minSalary "
			+ "WHERE e.id IN "
			+ "(SELECT e2.id FROM Employee e2 "
			+ "WHERE e.position.name = :position "
			+ "AND e2.company.id = :companyId "
			+ "AND e2.monthlySalary < :minSalary)")
	public int updateSalaries(long companyId, String position, int minSalary);
}
