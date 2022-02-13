package hu.webuni.hr.lacztam.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.lacztam.model.VacationPlanner;

public interface VacationRepository extends JpaRepository<VacationPlanner, Long>, JpaSpecificationExecutor<VacationPlanner> {

	@EntityGraph(attributePaths = { "vacationClaimer", "principal", "vacationState" })
	@Query("SELECT v FROM VacationPlanner v where id = :id")
	public VacationPlanner findWithClaimerAndPrincipalAndState(Long id);

//	@EntityGraph(attributePaths = { "vacationClaimer", "principal", "vacationState" })
	@Query("SELECT v FROM VacationPlanner v where id = :vacationId")
	public VacationPlanner findWithClaimerAndPrincipalAndState2(Long vacationId);
}
