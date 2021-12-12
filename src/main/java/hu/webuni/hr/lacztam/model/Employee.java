package hu.webuni.hr.lacztam.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Employee {
	private Long id;
	private String name;
	private String title;
	private int monthlySalary;
	private LocalDateTime beginningOfEmployment;

	public Employee() {
	}

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Employee(Long id, String name, String title, int monthlySalary, LocalDateTime beginningOfEmployment) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.monthlySalary = monthlySalary;
		this.beginningOfEmployment = beginningOfEmployment; 
	}
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public Employee(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.title = employee.getTitle();
		this.monthlySalary = employee.getMonthlySalary();
		this.beginningOfEmployment = employee.getBeginningOfEmployment(); 
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
		this.monthlySalary = (int) monthlySalary;
	}

	// returns with the LocalDateTime format
	public LocalDateTime getBeginningOfEmployment() {
		return beginningOfEmployment;
	}

	// returns a string with yyyy-mm-dd format
	public String string_getBeginningOfEmployment() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return beginningOfEmployment.format(format);
	}

	public void setBeginningOfEmployment(LocalDateTime beginningOfEmployment) {
		this.beginningOfEmployment = beginningOfEmployment;
	}
	
	public int getWorkedMonthsSinceStart() {
		LocalDateTime now = LocalDateTime.now();
		int currentDay = now.getDayOfMonth();
		int startDay = beginningOfEmployment.getDayOfMonth();

		long monthsBetweenBeginningOfWorkAndNow = ChronoUnit.MONTHS.between(beginningOfEmployment, now);
		
		// because every started month will be a completed worked month
		// despite the fact, that its not started from the first day of month
		if(startDay > currentDay)
			monthsBetweenBeginningOfWorkAndNow  +=1;

		return (int) monthsBetweenBeginningOfWorkAndNow;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", title=" + title + ", monthlySalary=" + monthlySalary
				+ ", beginningOfEmployment=" + beginningOfEmployment + "]";
	}
}
