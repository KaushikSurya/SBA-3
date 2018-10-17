package com.verizon.esd.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.esd.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {

	boolean existsByBasic(double basic);
	
	List<Employee> findAllByEmpName(String empName);
	List<Employee> findAllByDateOfJoining(LocalDate dateOfJoining);
}
