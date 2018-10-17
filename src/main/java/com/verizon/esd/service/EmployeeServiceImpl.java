package com.verizon.esd.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.esd.dao.EmployeeDao;
import com.verizon.esd.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao empdao;
	
	@Override
	public Employee addEmployee(Employee employee) {
		return empdao.save(employee);
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Employee employee=null;
		
		Optional<Employee>optEmpl=empdao.findById(empId);
		if(optEmpl.isPresent()){
			employee=optEmpl.get();
		}
		
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empdao.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return empdao.save(employee);
		}

	@Override
	public boolean deleteEmployee(int empId) {
		boolean isDeleted=false;
		if(empdao.existsById(empId)){
			empdao.deleteById(empId);
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public boolean existsByBasic(double basic) {
		return empdao.existsByBasic(basic);
	}

	@Override
	public List<Employee> findAllByEmpName(String empName) {
		return empdao.findAllByEmpName(empName);
	}

	@Override
	public List<Employee> findAllByDateOfJoining(LocalDate dateOfJoining) {
	return empdao.findAllByDateOfJoining(dateOfJoining);
	}

}
