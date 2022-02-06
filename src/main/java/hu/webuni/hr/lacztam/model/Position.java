package hu.webuni.hr.lacztam.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;

@Entity
public class Position {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Nullable
	private Qualification qualification;
	private int minSalary;
	
	@OneToMany(mappedBy = "position")
	private List<Employee> employee;

	public Position() {
	}

	public Position(String name, Qualification qualification, int minSalary) {
		this.name = name;
		this.qualification = qualification;
		this.minSalary = minSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public int getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
}
