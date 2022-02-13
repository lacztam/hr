package hu.webuni.hr.lacztam.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VacationPlannerFilterDto {

	private String vacationState;
	private String claimerName;
	private String principalName;

	private LocalDateTime approvalTime;
	private LocalDateTime submissionTime;
	
	private LocalDate vacationStart;
	private LocalDate vacationEnd;
	
	public VacationPlannerFilterDto() {
	}

	public String getVacationState() {
		return vacationState;
	}

	public String getClaimerName() {
		return claimerName;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public LocalDateTime getApprovalTime() {
		return approvalTime;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public LocalDate getVacationStart() {
		return vacationStart;
	}

	public LocalDate getVacationEnd() {
		return vacationEnd;
	}

	public void setVacationState(String vacationState) {
		this.vacationState = vacationState;
	}

	public void setClaimerName(String claimerName) {
		this.claimerName = claimerName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public void setApprovalTime(LocalDateTime approvalTime) {
		this.approvalTime = approvalTime;
	}

	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	public void setVacationStart(LocalDate vacationStart) {
		this.vacationStart = vacationStart;
	}

	public void setVacationEnd(LocalDate vacationEnd) {
		this.vacationEnd = vacationEnd;
	}
	
}
