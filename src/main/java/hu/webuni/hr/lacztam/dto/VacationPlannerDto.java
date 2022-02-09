package hu.webuni.hr.lacztam.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class VacationPlannerDto {

	private long id;
	
	@NotNull
	private Long vacationClaimerId;
	
	private Long principalId;
	private Boolean state = null;
	
	private LocalDateTime dateOfSubmission;
	private LocalDateTime dateOfApprove;
	
	@NotNull
	private LocalDate vacationStart;
	@NotNull
	private LocalDate vacationEnd;
	
	public VacationPlannerDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getVacationClaimerId() {
		return vacationClaimerId;
	}

	public void setVacationClaimerId(Long vacationClaimerId) {
		this.vacationClaimerId = vacationClaimerId;
	}

	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
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
