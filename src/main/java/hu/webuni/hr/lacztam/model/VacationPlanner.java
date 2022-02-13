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
	private VacationState vacationState;
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

	public Employee getVacationClaimer() {
		return vacationClaimer;
	}

	public Employee getPrincipal() {
		return principal;
	}

	public VacationState getVacationState() {
		return vacationState;
	}

	public LocalDateTime getDateOfSubmission() {
		return dateOfSubmission;
	}

	public LocalDateTime getDateOfApprove() {
		return dateOfApprove;
	}

	public LocalDate getVacationStart() {
		return vacationStart;
	}

	public LocalDate getVacationEnd() {
		return vacationEnd;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVacationClaimer(Employee vacationClaimer) {
		this.vacationClaimer = vacationClaimer;
	}

	public void setPrincipal(Employee principal) {
		this.principal = principal;
	}

	public void setVacationState(VacationState vacationState) {
		this.vacationState = vacationState;
	}

	public void setDateOfSubmission(LocalDateTime dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public void setDateOfApprove(LocalDateTime dateOfApprove) {
		this.dateOfApprove = dateOfApprove;
	}

	public void setVacationStart(LocalDate vacationStart) {
		this.vacationStart = vacationStart;
	}

	public void setVacationEnd(LocalDate vacationEnd) {
		this.vacationEnd = vacationEnd;
	}

	@Override
	public String toString() {
		return "VacationPlanner\n[id=" + id + ", vacationClaimer=" + vacationClaimer + ", principal=" + principal
				+ ", vacationState=" + vacationState + ", dateOfSubmission=" + dateOfSubmission + ", dateOfApprove="
				+ dateOfApprove + ", vacationStart=" + vacationStart + ", vacationEnd=" + vacationEnd + "]";
	}
}
