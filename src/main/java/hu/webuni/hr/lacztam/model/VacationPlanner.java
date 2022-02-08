package hu.webuni.hr.lacztam.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class VacationPlanner {

	@Id
	private Long id;
	
	@OneToOne(mappedBy = "planner", fetch = FetchType.EAGER)
	private Employee vacationClaimer;
	
	private Employee principal;
	private Boolean state = null;
	private LocalDateTime dateOfSubmission;
	private LocalDateTime vacationStart;
	private LocalDateTime vacationEnd;
	
	public VacationPlanner() {
		this.dateOfSubmission = LocalDateTime.now();
	}

	public VacationPlanner(Long id, Employee vacationClaimer, Boolean state, Employee principal,
			LocalDateTime vacationStart, LocalDateTime vacationEnd) {
		this.id = id;
		this.vacationClaimer = vacationClaimer;
		this.state = state;
		this.principal = principal;
		this.dateOfSubmission = LocalDateTime.now();
		this.vacationStart = vacationStart;
		this.vacationEnd = vacationEnd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getVacationClaimer() {
		return vacationClaimer;
	}

	public void setVacationClaimer(Employee vacationClaimer) {
		this.vacationClaimer = vacationClaimer;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public LocalDateTime getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(LocalDateTime dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public LocalDateTime getVacationStart() {
		return vacationStart;
	}

	public void setVacationStart(LocalDateTime vacationStart) {
		this.vacationStart = vacationStart;
	}

	public LocalDateTime getVacationEnd() {
		return vacationEnd;
	}

	public void setVacationEnd(LocalDateTime vacationEnd) {
		this.vacationEnd = vacationEnd;
	}

	public Employee getPrincipal() {
		return principal;
	}

	public void setPrincipal(Employee principal) {
		this.principal = principal;
	}
}
