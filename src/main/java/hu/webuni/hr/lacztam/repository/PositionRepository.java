package hu.webuni.hr.lacztam.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import hu.webuni.hr.lacztam.model.Position;

public interface PositionRepository extends JpaRepository<Position, Integer>{
	
	public List<Position> findByName(String name);
	
	public Position findByEmployeeName(String name);
	public Position findByEmployeeId(int id);

}
