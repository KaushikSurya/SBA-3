package com.verizon.esd.restapi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.esd.model.Employee;
import com.verizon.esd.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/Employees")
public class EmployeeApi {

	@Autowired
	private EmployeeService employeeserv;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeserv.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int EmployeeId) {
		ResponseEntity<Employee> resp;

		Employee e = employeeserv.getEmployeeById(EmployeeId);
		if (e == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(e, HttpStatus.OK);
		return resp;
	}

	@GetMapping("/{field}/{srhValue}")
	public ResponseEntity<List<Employee>> getAllEmployeess(@PathVariable("field") String fieldBy,
			@PathVariable("srhValue") String searchValue) {

		ResponseEntity<List<Employee>> resp=null;

		switch (fieldBy) {
		case "empName":
			List<Employee> results = employeeserv.findAllByEmpName(searchValue);
			if (results != null) {
				resp = new ResponseEntity<>(results, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "doj":
			List<Employee> resultsdoj = employeeserv.findAllByDateOfJoining(LocalDate.parse(searchValue));
			if (resultsdoj != null) {
				resp = new ResponseEntity<>(resultsdoj, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
			default:
				resp=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				break;
		}

		return resp;
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee Employee) {
		ResponseEntity<Employee> resp = null;

		if (employeeserv.existsByBasic(Employee.getBasic()))
			resp = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

		if (resp == null) {
			Employee c = employeeserv.addEmployee(Employee);
			if (c == null)
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<>(c, HttpStatus.OK);
		}
		return resp;
	}

	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		ResponseEntity<Employee> resp = null;

		Employee c = employeeserv.getEmployeeById(employee.getEmpId());

		if (!((employee.getBasic())==c.getBasic()))
			if (employeeserv.existsByBasic(employee.getBasic())) {
				resp = new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
			}

		if (resp == null) {
			c = employeeserv.updateEmployee(employee);
			if (c == null)
				resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<>(c, HttpStatus.OK);
		}
		return resp;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int EmployeeId) {
		ResponseEntity<Void> resp = null;
		if (employeeserv.deleteEmployee(EmployeeId))
			resp = new ResponseEntity<>(HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return resp;
	}

}
