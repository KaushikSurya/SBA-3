package com.verizon.esd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.esd.model.Department;
import com.verizon.esd.model.Employee;
import com.verizon.esd.restapi.EmployeeApi;
import com.verizon.esd.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeApi.class)
public class EmployeeApiTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private EmployeeService empServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());

		when(empServiceMock.getAllEmployees()).thenReturn(empList);

		mockMvc.perform(get("/Employees")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetEmployeeById() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();
		int empId = 10;

		when(empServiceMock.getEmployeeById(empId)).thenReturn(emp);

		mockMvc.perform(get("/Employees/10")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetAllEmployeess() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());

		when(empServiceMock.getAllEmployees()).thenReturn(empList);

		mockMvc.perform(get("/Employees/empName/Devesh")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testAddEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee("PooMan", 123, 12, LocalDate.now(), Department.Network);
		;

		when(empServiceMock.addEmployee(emp)).thenReturn(emp);

		mockMvc.perform(post("/Employees", emp)).andExpect(status().is4xxClientError()).andDo(print());

	}

	/*@Test
	public void testUpdateEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		int empId = 1;
		Employee emp = new Employee();
		when(empServiceMock.getEmployeeById(empId)).thenReturn(emp);

		System.out.println(emp.getEmpId());

		// when(emp.setDept(Department.Database)).thenReturn();

		when(empServiceMock.updateEmployee(emp)).thenReturn(emp);

		mockMvc.perform(put("/Employees", emp)).andExpect(status().isOk()).andDo(print());

	}*/

	@Test	
	public void testUpdateEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();
		
		Employee employee = new Employee();
		employee.setEmpId(1);
		employee.setEmpName("Rajeev");
		employee.setBasic(20000);
		employee.setDept(Department.HR);
		employee.setHra(82);
		employee.setDateOfJoining(LocalDate.now());
		//Employee employee = new Employee();
		Employee c = empServiceMock.getEmployeeById(employee.getEmpId());
		//Employee c = empServiceMock.getEmployeeById(1);	
		//long empId;
		when(empServiceMock.updateEmployee(employee)).thenReturn(c);
		mockMvc.perform(put("/Employees",employee)).		andExpect(status().isOk()).		andDo(print());	
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		int empId = 10;

		when(empServiceMock.deleteEmployee(empId)).thenReturn(true);

		mockMvc.perform(delete("/Employees/10")).andExpect(status().isOk()).andDo(print());

	}

}
