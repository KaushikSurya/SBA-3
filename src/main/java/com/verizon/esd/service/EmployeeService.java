package com.verizon.esd.service;

import java.time.LocalDate;
import java.util.List;

import com.verizon.esd.model.Employee;

public interface EmployeeService {
	Employee addEmployee(Employee employee);
	Employee getEmployeeById(int empId);
	List<Employee>getAllEmployees();
	Employee updateEmployee(Employee Employee);
	boolean deleteEmployee(int empId);
	
	boolean existsByBasic(double basic);
	List<Employee> findAllByEmpName(String empName);
	List<Employee> findAllByDateOfJoining(LocalDate dateOfJoining);
}
