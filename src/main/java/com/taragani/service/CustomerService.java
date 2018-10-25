package com.taragani.service;

import java.util.List;

import com.taragani.model.Customer;

public interface CustomerService {
	public Customer addCustomer(Customer customer);
	public boolean removeCustomer(int cid);
	public Customer updateCustomer(Customer customer);
	public Customer getCustomer(int cid);
	public Customer getCustomerByName(String name);
	public List<Customer> getAllCustomers();
	public boolean exists(int cid);
	public boolean existsByName(String name);
	public Customer getCustomerMyMbno(String mbno);
}