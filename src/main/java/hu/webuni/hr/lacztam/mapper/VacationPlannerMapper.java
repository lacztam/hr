package hu.webuni.hr.lacztam.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.hr.lacztam.dto.VacationPlannerDto;
import hu.webuni.hr.lacztam.model.VacationPlanner;

@Mapper(componentModel = "spring")
public interface VacationPlannerMapper {

	@Mapping(target = "vacationClaimer", ignore = true)
	@Mapping(target = "principal", ignore = true)
	VacationPlanner dtoToVacationPlanner(VacationPlannerDto dto);
	
	
	@Mapping(target = "vacationClaimerId", source = "vacationClaimer.id")
	@Mapping(target = "principalId", source = "principal.id")
	VacationPlannerDto vacationPlannerToDto(VacationPlanner planner);

	List<VacationPlanner> dtosToPlanners(List<VacationPlannerDto> dtos);
	
	List<VacationPlannerDto> plannersToDtos(List<VacationPlanner> planners);
}
