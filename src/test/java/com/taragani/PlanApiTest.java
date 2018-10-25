package com.taragani;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.taragani.model.Plan;
import com.taragani.service.PlanApi;
import com.taragani.service.PlanService;
//import com.taragani.util.TestUtil.TestUtil;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PlanApi.class)
public class PlanApiTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private PlanService planServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllPlans() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(planList);

		mockMvc.perform(get("/plans")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetPlanById() throws Exception {
		assertThat(this.planServiceMock).isNotNull();
		String pTitle = "TL2505";
	

		Plan planAdded = new Plan();

		planAdded.setpTitle("TL2505");
		planAdded.setSpeed(5);
		planAdded.setMaxUsage(250);
		planAdded.setCharge(1250.0);

		when(planServiceMock.getPlan(pTitle)).thenReturn(planAdded);

		mockMvc.perform(get("/plans/TL2505")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetPlansBySpeed() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(planList);
		
		mockMvc.perform(get("/plans/speed/5")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testGetPlansByMaxUsage() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(planList);
		
		mockMvc.perform(get("/plans/maxUsage/250")).andExpect(status().isOk()).andDo(print());

	}

	/*@Test
	public void testAddPlan() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		Plan plan = new Plan();

		plan.setEmpName("Raima");
		plan.setBasic(8977);
		plan.setHra(45);
		plan.setDateOfJoining(null);
		plan.setDept(Department.HR);

		Plan planAdded = new Plan();
		planAdded.setEmpId(14);
		planAdded.setEmpName("Raima");
		planAdded.setBasic(8977);
		planAdded.setHra(45);
		planAdded.setDateOfJoining(null);
		planAdded.setDept(Department.HR);

		System.out.println(plan);

		when(planServiceMock.addPlan(Mockito.any(Plan.class))).thenReturn(planAdded);

		mockMvc.perform(post("/Plans").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(plan))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testUpdatePlan() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		Plan plan = new Plan();
		plan.setEmpId(13);
		plan.setEmpName("RaimaBaby");
		plan.setBasic(8977);
		plan.setHra(45);
		plan.setDateOfJoining(null);
		plan.setDept(Department.Network);

		int planId = 13;
		

		Plan planAdded = new Plan();

		planAdded.setEmpId(13);
		planAdded.setEmpName("Aditya");
		planAdded.setBasic(8977);
		planAdded.setHra(45);
		planAdded.setDateOfJoining(LocalDate.of(2018, 10, 17));
		planAdded.setDept(Department.HR);

		when(planServiceMock.getPlanById(planId)).thenReturn(planAdded);

		when(planServiceMock.updatePlan(Mockito.any(Plan.class))).thenReturn(plan);

		mockMvc.perform(put("/Plans").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(plan))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));


	}

	@Test
	public void testDeletePlan() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		int planId = 10;

		when(planServiceMock.deletePlan(planId)).thenReturn(true);

		mockMvc.perform(delete("/Plans/10")).andExpect(status().isOk()).andDo(print());

	}*/

}
