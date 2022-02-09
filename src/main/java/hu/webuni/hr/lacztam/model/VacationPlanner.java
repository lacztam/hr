package hu.webuni.hr.lacztam.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class VacationPlanner {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Employee vacationClaimer;
	
	@ManyToOne
	private Employee principal;
	private Boolean state = null;
	
	private LocalDateTime dateOfSubmission;
	private LocalDateTime dateOfApprove;
	
	private LocalDate vacationStart;
	private LocalDate vacationEnd;
	
	public VacationPlanner() {
		this.dateOfSubmission = LocalDateTime.now();
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

	public Employee getPrincipal() {
		return principal;
	}

	public void setPrincipal(Employee principal) {
		this.principal = principal;
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

	public LocalDateTime getDateOfApprove() {
		return dateOfApprove;
	}

	public void setDateOfApprove(LocalDateTime dateOfApprove) {
		this.dateOfApprove = dateOfApprove;
	}

	public LocalDate getVacationStart() {
		return vacationStart;
	}

	public void setVacationStart(LocalDate vacationStart) {
		this.vacationStart = vacationStart;
	}

	public LocalDate getVacationEnd() {
		return vacationEnd;
	}

	public void setVacationEnd(LocalDate vacationEnd) {
		this.vacationEnd = vacationEnd;
	}

}
