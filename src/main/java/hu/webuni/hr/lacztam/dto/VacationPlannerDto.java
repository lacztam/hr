package hu.webuni.hr.lacztam.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class VacationPlannerDto {

	private long id;
	@NotNull
	private Long vacationClaimerId;
	private Long principalId;
	private String state;
	private LocalDateTime dateOfSubmission;
	private LocalDateTime dateOfApprove;
	@NotNull
	private LocalDate vacationStart;
	@NotNull
	private LocalDate vacationEnd;
	
	private String claimerPosition;
	
	public VacationPlannerDto() {
	}

	public long getId() {
		return id;
	}

	public Long getVacationClaimerId() {
		return vacationClaimerId;
	}

	public Long getPrincipalId() {
		return principalId;
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

	public void setId(long id) {
		this.id = id;
	}

	public void setVacationClaimerId(Long vacationClaimerId) {
		this.vacationClaimerId = vacationClaimerId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getClaimerPosition() {
		return claimerPosition;
	}

	public void setClaimerPosition(String claimerPosition) {
		this.claimerPosition = claimerPosition;
	}

}
