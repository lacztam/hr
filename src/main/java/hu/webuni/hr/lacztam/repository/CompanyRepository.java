package hu.webuni.hr.lacztam.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import hu.webuni.hr.lacztam.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	

	@Query("SELECT DISTINCT c FROM Company c JOIN c.employeesList e WHERE e.monthlySalary > :minSalary")
	public Page<Company> findByEmployeeWithSalaryHigherThan(Pageable pageable,int minSalary);
	
	@Query("SELECT c FROM Company c WHERE SIZE(c.employeesList) > :minEmployeeCount")
	public List<Company> findByEmployeeCountHigherThan(int minEmployeeCount);
	
	@Query("SELECT e.position.name AS position, avg(e.monthlySalary) AS averageSalary "
			+ "FROM Company c "
			+ "INNER JOIN c.employeesList e "
			+ "WHERE c.id = :companyId "
			+ "GROUP BY e.position.name "
			+ "ORDER BY avg(e.monthlySalary) DESC")
	public List<AverageSalaryByPosition> findAverageSalariesByPosition(long companyId);
	
	@Query("UPDATE Employee e "
			+ "SET e.monthlySalary = :minSalary "
			+ "WHERE e.position.name = :position "
			+ "AND e.company.id = :companyId "
			+ "AND e.monthlySalary < :minSalary")
	public int updateSalaries(long companyId, String position, int minSalary);
	
	
	public Company findByName(String companyName);

	@EntityGraph(attributePaths = { "employeesList", "employeesList.position" })
	@Query("SELECT c FROM Company c")
	public List<Company> namedQuery1();
	
	@EntityGraph("Company.fullList")
	@Query("SELECT c FROM Company c")
	public List<Company> namedQuery2();
	
}
