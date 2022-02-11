package hu.webuni.hr.lacztam.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int monthlySalary;
	private LocalDateTime beginningOfEmployment;
	
	@ManyToOne
	private Position position;
	 
	@ManyToOne
	private Company company;
	
	@OneToMany(mappedBy = "vacationClaimer")
	private List<VacationPlanner> vacationPlanners = new ArrayList<VacationPlanner>();
	
	@ManyToOne
	private Employee manager;
	
	private String username;
	private String password; 
	
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

	public void addVacationToVacationPlannerList(VacationPlanner vacationPlanner) {
		if(this.vacationPlanners == null)
			this.vacationPlanners = new ArrayList<>();
		
		this.vacationPlanners.add(vacationPlanner);
		vacationPlanner.setVacationClaimer(this);
	}
	
	public List<VacationPlanner> getPlannerList() {
		return vacationPlanners;
	}

	public void setPlannerList(List<VacationPlanner> plannerList) {
		this.vacationPlanners = plannerList;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<VacationPlanner> getVacationPlanners() {
		return vacationPlanners;
	}

	public void setVacationPlanners(List<VacationPlanner> vacationPlanners) {
		this.vacationPlanners = vacationPlanners;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}