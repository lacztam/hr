package hu.webuni.hr.lacztam.web;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.lacztam.dto.VacationPlannerDto;
import hu.webuni.hr.lacztam.mapper.VacationPlannerMapper;
import hu.webuni.hr.lacztam.model.VacationPlanner;
import hu.webuni.hr.lacztam.repository.EmployeeRepository;
import hu.webuni.hr.lacztam.repository.VacationRepository;
import hu.webuni.hr.lacztam.service.VacationPlannerService;

@RestController
@RequestMapping("/api/vacations")
public class VacationController {
	
	@Autowired VacationRepository vacationRepository;
	@Autowired VacationPlannerMapper vacationPlannerMapper;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired VacationPlannerMapper VPmapper;
	@Autowired VacationPlannerService vacationPlannerService;
	
	@GetMapping
	public List<VacationPlannerDto> vacationById(){
		List<VacationPlannerDto> vacationPlanners = VPmapper.plannersToDtos(vacationRepository.findAll());
		return vacationPlanners;
	}
	
	@PostMapping
	public VacationPlannerDto postVacation(@RequestBody @Valid VacationPlannerDto vacationPlannerDto) {
		VacationPlanner vacationPlanner;
		try {
			vacationPlanner = vacationPlannerService.addVacation(vacationPlannerMapper.dtoToVacationPlanner(vacationPlannerDto), vacationPlannerDto.getVacationClaimerId());
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return vacationPlannerMapper.vacationPlannerToDto(vacationPlanner);
	}
	
	@PutMapping(value = "/{id}/approval", params = { "status", "approverId" })
	public VacationPlannerDto approveVacation(@PathVariable long id, @RequestParam long approverId, @RequestParam boolean status) {
		VacationPlanner vacationPlanner;
		try {
			vacationPlanner = vacationPlannerService.approveVacation(id, approverId, status);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return vacationPlannerMapper.vacationPlannerToDto(vacationPlanner);
	}
	
	@PutMapping("/18/a")
	public VacationPlannerDto approveVacation() {
		VacationPlanner vacationPlanner;
		try {
			vacationPlanner = vacationPlannerService.approveVacation(18L, 12, false);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return vacationPlannerMapper.vacationPlannerToDto(vacationPlanner);
	}

}
