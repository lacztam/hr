package hu.webuni.hr.lacztam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public enum CompanyType {
	BT, KFT, ZRT, NYRT;
}

//@Entity
//public class CompanyType {
//	
//	@Id
//	@GeneratedValue
//	private int id;
//	private String name;
//
//	public CompanyType() {
//	}
//
//	public CompanyType(int id, String name) {
//		this.id = id;
//		this.name = name;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//}