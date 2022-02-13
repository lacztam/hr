package hu.webuni.hr.lacztam.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;

import hu.webuni.hr.lacztam.dto.EmployeeDto;
import hu.webuni.hr.lacztam.dto.VacationPlannerDto;
import hu.webuni.hr.lacztam.dto.VacationPlannerFilterDto;
import hu.webuni.hr.lacztam.mapper.VacationPlannerMapper;
import hu.webuni.hr.lacztam.model.Employee;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.model.VacationState;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;
import hu.webuni.hr.lacztam.service.VacationPlannerService;

@RestController
@RequestMapping("/api/vacations")
public class VacationController {
	
	@Autowired VacationRepository vacationRepository;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired VacationPlannerMapper vacationPlannerMapper;
	@Autowired VacationPlannerService vacationPlannerService;
	
	@GetMapping
	public List<VacationPlannerDto> vacationById(){
		List<VacationPlannerDto> vacationPlanners = vacationPlannerMapper.plannersToDtos(vacationRepository.findAll());
		return vacationPlanners;
	}
	
	@PostMapping
	public VacationPlannerDto postVacation(@RequestBody @Valid VacationPlannerDto vacationPlannerDto) {
		System.out.println(vacationPlannerDto.toString());
		
		VacationPlanner vacationPlanner;
		try {
			vacationPlanner = vacationPlannerService.addVacation(vacationPlannerMapper.dtoToVacationPlanner(vacationPlannerDto), vacationPlannerDto.getVacationClaimerId());
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return vacationPlannerMapper.vacationPlannerToDto(vacationPlanner);
	}
	
	@PreAuthorize("#approverId == authentication.principal.employee.employeeId")
	@PutMapping(value = "/{vacationId}/approval", params = { "status", "approverId" })
	public VacationPlannerDto approveVacation(@PathVariable long vacationId, 
												@RequestParam long approverId, 
												@RequestParam boolean status) {
		VacationPlanner vacationPlanner;
		VacationState vacationState;
		vacationState = status ? VacationState.APPROVED_BY_MANAGER : VacationState.CANCELLED_BY_MANAGER; 
		
		try {
			vacationPlanner = vacationPlannerService.approveVacation(vacationId, approverId, vacationState);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return vacationPlannerMapper.vacationPlannerToDto(vacationPlanner);
	}
	
	@PreAuthorize("#employeeId == authentication.principal.employee.employeeId")
	@PutMapping("/modifyVacation/{vacationId}/withEmployee/{employeeId}")
	public VacationPlannerDto modifyVacation(@PathVariable Long vacationId, 
											@RequestBody VacationPlannerDto vacationPlannerDto, 
											@PathVariable long employeeId) {
		VacationPlanner vacation = null;
		try{
			vacation = mapDtoToVacation(vacationPlannerDto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(vacation.getVacationState() == VacationState.APPROVED_BY_MANAGER)
			vacation.setDateOfApprove(LocalDateTime.now());
		
		if(true) {
			VacationPlanner modifyVacatoin = vacationPlannerService.modifyVacation(vacationId, vacation);
			return vacationPlannerMapper.vacationPlannerToDto(modifyVacatoin);
		}else {
			throw new AccessDeniedException("Vacation is in approved state, cannot delete it.");
		}
	}
	
	@GetMapping("/dtoToVacation")
	public VacationPlannerDto createTempVacation(@RequestBody VacationPlannerDto vacationPlannerDto) {
		VacationPlanner vacationPlanner = mapDtoToVacation(vacationPlannerDto);
		VacationPlannerDto vacation = vacationPlannerMapper.vacationPlannerToDto(vacationPlanner);
		VacationPlanner vacationTemp = vacationPlannerMapper.dtoToVacationPlanner(vacation);
		System.out.println("\n\nvacationTemp:\n\n" + vacationTemp.toString());
		
		return vacation;
	}
	
	@PreAuthorize("#employeeId == authentication.principal.employee.employeeId")
	@DeleteMapping("/deleteVacation/{vacationId}/withEmployee/{employeeId}")
	public void deleteVacation(@PathVariable long vacationId, @PathVariable long employeeId) {
		VacationPlanner vacationPlanner = vacationRepository.findById(vacationId).get();
		
		VacationState vacationState = vacationPlanner.getVacationState();
		
		switch (vacationState) {
		case APPROVED_BY_MANAGER:
			throw new AccessDeniedException("Vacation is in approved state, cannot delete it.");
		case PENDING:
		case CANCELLED_BY_EMPLOYEE:
		case CANCELLED_BY_MANAGER:
			vacationRepository.delete(vacationPlanner);
		default:
			break;
		}
	}
	
	@GetMapping("/{vacationId}/findWithTwoEmployeesAndState")
	public VacationPlanner getWithEmployeesAndState(@PathVariable Long vacationId) {
		VacationPlanner vacation = vacationRepository.findWithClaimerAndPrincipalAndState(vacationId);
		return vacation;
	}
	
	@GetMapping("/{vacationId}/findWithTwoEmployeesAndState2")
	public VacationPlannerDto getWithEmployeesAndState2(@PathVariable Long vacationId) {
		VacationPlanner vacation = vacationRepository.findWithClaimerAndPrincipalAndState2(vacationId);
		return vacationPlannerMapper.vacationPlannerToDto(vacation);
	}
	
	@GetMapping("/spec")
	public List<VacationPlannerFilterDto> getSpecifiedVacation(
			@RequestParam(required = false) String state,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate submissionStart,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate submissionEnd,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationStart,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationEnd){
		
		List<VacationPlannerFilterDto> specifiedVacations = 
				vacationPlannerService.getSpecifiedVacations(
						state, 
						name, 
						submissionStart, 
						submissionEnd, 
						vacationStart, 
						vacationEnd
						);
		
		return specifiedVacations;
	}
	
	protected VacationPlanner mapDtoToVacation(VacationPlannerDto dto) {
		VacationPlanner vacationPlanner = new VacationPlanner();
		
		LocalDateTime dateOfApprove = dto.getDateOfApprove();
		LocalDateTime dateOfSubmission = dto.getDateOfSubmission();
		VacationState state = VacationState.valueOf(dto.getState());
		System.out.println(state);
		System.out.println("dto.getState():" + dto.getState());
		LocalDate vacationEnd = dto.getVacationEnd();
		LocalDate vacationstart = dto.getVacationStart();
		Employee principal = employeeRepository.findById(dto.getPrincipalId()).get();
		Employee claimer = null;
		
		Long vacationClaimerId = dto.getVacationClaimerId();
		if(vacationClaimerId != null)
			claimer = employeeRepository.findById(vacationClaimerId).get();
		
		if(dateOfApprove != null) 
			vacationPlanner.setDateOfApprove( dateOfApprove );
		
		if(dateOfSubmission != null)
			vacationPlanner.setDateOfSubmission( dateOfSubmission );

		if( state != null) 
			vacationPlanner.setVacationState(state);
		
		if(vacationEnd != null && vacationstart != null) {
			vacationPlanner.setVacationEnd( vacationEnd );
			vacationPlanner.setVacationStart( vacationstart );
		}

		if(principal != null)
			vacationPlanner.setPrincipal(principal);
        
		if(claimer != null)
			vacationPlanner.setVacationClaimer(claimer);
		
        return vacationPlanner;
	}
	
}
