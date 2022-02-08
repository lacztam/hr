package hu.webuni.hr.lacztam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.lacztam.model.VacationPlanner;

public interface VacationRepository extends JpaRepository<VacationPlanner, Long>{

}
