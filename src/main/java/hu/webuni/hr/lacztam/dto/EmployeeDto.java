package hu.webuni.hr.lacztam.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EmployeeDto {
	private Long id;
	private String name;
	private String title;
	private int monthlySalary;
	private LocalDateTime beginningOfEmployment;

	public EmployeeDto() {
	}
	
	public EmployeeDto(Long id) {
		this.id = id;
		this.name = "default name";
		this.title = "title";
		this.monthlySalary = 0;
		this.beginningOfEmployment = LocalDateTime.of(1901, 01, 01, 00, 00);
	}
	
	// first constructor will assign the 1901-01-01 date value
	// for the beginning of employment variable
	public EmployeeDto(Long id, String name, String title, int monthlySalary) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.monthlySalary = monthlySalary;
		this.beginningOfEmployment = LocalDateTime.of(1901, 01, 01, 00, 00); // format: yyyy-MM-dd-HH-mm-ss.zzz
	}

	// in the second constructor, you may set the beginning of employment variable
	// with the year, month, day arguments
	public EmployeeDto(Long id, String name, String title, int monthlySalary, int year, int month, int day) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.monthlySalary = monthlySalary;
		this.beginningOfEmployment = LocalDateTime.of(year, month, day, 00, 00);
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
		return "\nEmployeeDto.toString() [\nid=" + id + "\nname=" + name + "\ntitle=" + title + "\nmonthlySalary="
				+ monthlySalary + "\nbeginningOfEmployment=" + beginningOfEmployment + 
				"\ngetWorkedMonthsSinceStart()=" + getWorkedMonthsSinceStart() +"\n]";
	}
}
