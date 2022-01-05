package hu.webuni.hr.lacztam.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import hu.webuni.hr.lacztam.service.constraint.ValidEmployeeDto;

@ValidEmployeeDto
public class EmployeeDto {
	private Long id;
	private String name;
	private String title;
	private int monthlySalary;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginningOfEmployment;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public EmployeeDto(Long id, String name, String title, int monthlySalary, LocalDateTime beginningOfEmployment) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.monthlySalary = monthlySalary;
		this.beginningOfEmployment = beginningOfEmployment;
	}
	
	public EmployeeDto() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public LocalDateTime getBeginningOfEmployment() {
		return beginningOfEmployment;
	}
	public void setBeginningOfEmployment(LocalDateTime beginningOfEmployment) {
		this.beginningOfEmployment = beginningOfEmployment;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", title=" + title + ", monthlySalary=" + monthlySalary
				+ ", beginningOfEmployment=" + beginningOfEmployment + "]";
	}
}