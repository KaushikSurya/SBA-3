package com.taragani;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.taragani.model.Customer;
import com.taragani.service.CustomerApi;
import com.taragani.service.CustomerService;
import com.taragani.util.TestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CustomerApi.class)
public class CustomerApiTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private CustomerService customerServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		assertThat(this.customerServiceMock).isNotNull();

		List<Customer> empList = new ArrayList<>();
		empList.add(new Customer());

		when(customerServiceMock.getAllCustomers()).thenReturn(empList);

		mockMvc.perform(get("/customers")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetCustomerById() throws Exception {
		assertThat(this.customerServiceMock).isNotNull();
		int cid = 9;

		Customer customerAdded = new Customer();

		customerAdded.setCid(9);
		customerAdded.setName("Himalesh");
		customerAdded.setpTitle("TL75010");
		customerAdded.setMbno("8750018197");
		customerAdded.setTimeSlot("4-6");
		customerAdded.setAddress("Delhi");
		customerAdded.setDor(LocalDate.now());

		when(customerServiceMock.getCustomer(cid)).thenReturn(customerAdded);

		mockMvc.perform(get("/customers/9")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetCustomerByName() throws Exception {
		assertThat(this.customerServiceMock).isNotNull();
		
		String name="Himalesh";
		Customer customerAdded = new Customer();
		
		customerAdded.setCid(9);
		customerAdded.setName("Himalesh");
		customerAdded.setpTitle("TL75010");
		customerAdded.setMbno("8750018197");
		customerAdded.setTimeSlot("4-6");
		customerAdded.setAddress("Delhi");
		customerAdded.setDor(LocalDate.now());

		when(customerServiceMock.getCustomerByName(name)).thenReturn(customerAdded);

		mockMvc.perform(get("/customers/name/Himalesh")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testAddCustomer() throws Exception {
		assertThat(this.customerServiceMock).isNotNull();

		Customer emp = new Customer();

		Customer customerAdded = new Customer();

		customerAdded.setCid(9);
		customerAdded.setName("Himalesh");
		customerAdded.setpTitle("TL75010");
		customerAdded.setMbno("8750018197");
		customerAdded.setTimeSlot("4-6");
		customerAdded.setAddress("Delhi");
		customerAdded.setDor(LocalDate.now());

		System.out.println(emp);

		when(customerServiceMock.addCustomer(Mockito.any(Customer.class))).thenReturn(customerAdded);

		mockMvc.perform(post("/customers").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testUpdateCustomer() throws Exception {
		assertThat(this.customerServiceMock).isNotNull();
		
		Customer custm = new Customer();
		
		custm.setCid(10);
		custm.setName("Burra");
		custm.setpTitle("TL75015");
		custm.setMbno("8750018197");
		custm.setTimeSlot("6-8");
		custm.setAddress("Hyderabad");
		custm.setDor(null);

		int cid = 10;

		Customer customerAdded = new Customer();

		customerAdded.setCid(10);
		customerAdded.setName("Burra");
		customerAdded.setpTitle("TL75010");
		customerAdded.setMbno("8750018197");
		customerAdded.setTimeSlot("6-8");
		customerAdded.setAddress("Hyderabad");
		customerAdded.setDor(LocalDate.now());

		when(customerServiceMock.getCustomer(cid)).thenReturn(customerAdded);

		when(customerServiceMock.updateCustomer(Mockito.any(Customer.class))).thenReturn(custm);

		mockMvc.perform(put("/customers").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(custm))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testDeleteCustomer() throws Exception {
		assertThat(this.customerServiceMock).isNotNull();

		int cid = 5;

		when(customerServiceMock.removeCustomer(cid)).thenReturn(true);

		mockMvc.perform(delete("/customers/5")).andExpect(status().isOk()).andDo(print());

	}

}
