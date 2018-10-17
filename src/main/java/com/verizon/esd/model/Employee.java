package com.verizon.esd.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empId;
	
	@NotEmpty(message="Name cannot be Empty")
	@Size(max=30,message="Name must be less than 30 chars")
	private String empName;
	
	@NotNull(message="Basic Slary cannot be Null")
	private double basic;
	
	@NotNull(message="HRA cannot be Null")
	private double hra;
	
	@DateTimeFormat(iso=ISO.DATE)
	@NotNull(message="HRA cannot be Null")
	@Column(name="Joining")
	private LocalDate dateOfJoining;
	
	@NotNull(message="Department cannot be Null")
	@Enumerated(EnumType.STRING)
	private Department dept;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Employee(
			@NotEmpty(message = "Name cannot be Empty") @Size(max = 30, message = "Name must be less than 30 chars") String empName,
			@NotNull(message = "Basic Slary cannot be Null") double basic,
			@NotNull(message = "HRA cannot be Null") double hra,
			@NotNull(message = "HRA cannot be Null") LocalDate dateOfJoining,
			@NotNull(message = "Department cannot be Null") Department dept) {
		super();
		this.empName = empName;
		this.basic = basic;
		this.hra = hra;
		this.dateOfJoining = dateOfJoining;
		this.dept = dept;
	}

	public Employee() {
		super();
	}
	
	
}
