package hu.webuni.hr.lacztam.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import hu.webuni.hr.lacztam.dto.VacationPlannerDto;
import hu.webuni.hr.lacztam.dto.VacationPlannerFilterDto;
import hu.webuni.hr.lacztam.model.VacationPlanner;

@Mapper(componentModel = "spring")
public interface VacationPlannerMapper {
	
	@Mapping(source = "vacationClaimer.employeeId", target = "vacationClaimerId")
	@Mapping(source = "principal.employeeId", target = "principalId")
	@Mapping(source = "vacationState", target = "state")
	@Mapping(source = "vacationClaimer.position.name", target = "claimerPosition")
	VacationPlannerDto vacationPlannerToDto(VacationPlanner vacationPlanner);
	
	@Mapping(target = "vacationClaimer", ignore = true)
	@Mapping(target = "principal", ignore = true)
	@Mapping(target = "vacationState", source = "state")
	VacationPlanner dtoToVacationPlanner(@Valid VacationPlannerDto dto);
	
	List<VacationPlanner> dtosToPlanners(List<VacationPlannerDto> vacationPlannerDtos);
	
	List<VacationPlannerDto> plannersToDtos(List<VacationPlanner> vacationPlanners);

	@Mapping(target = "vacationState", source = "vacationState")
	@Mapping(target = "claimerName", source = "vacationClaimer.name" )
	@Mapping(target = "principalName", source = "principal.name")
	@Mapping(target = "approvalTime", source = "dateOfApprove")
	@Mapping(target = "submissionTime", source = "dateOfSubmission")
	VacationPlannerFilterDto vacationToFilterDto(VacationPlanner vacationPlanner);
	
	List<VacationPlannerFilterDto> vacationsToFilterDtos(List<VacationPlanner> vacationPlanners);
}
