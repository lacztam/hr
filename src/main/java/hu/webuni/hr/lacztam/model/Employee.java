package hu.webuni.hr.lacztam.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int monthlySalary;
	private LocalDateTime beginningOfEmployment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Position position;
	 
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	private Company company;

	public Employee() {
	}

	public Employee(Long id, String name, int monthlySalary, LocalDateTime beginningOfEmployment) {
		this.id = id;
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.beginningOfEmployment = beginningOfEmployment; 
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
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

}