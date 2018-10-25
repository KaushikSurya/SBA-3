package com.taragani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taragani.dao.CustomerRepo;
import com.taragani.model.Customer;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	public CustomerRepo customerRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		customerRepo.save(customer);
		return customer;
	}

	@Override
	public boolean removeCustomer(int cid) {
		boolean isDeleted=false;
		if(customerRepo.existsByCid(cid)) {
			customerRepo.deleteByCid(cid);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		if(customerRepo.existsByCid(customer.getCid())) {
			customerRepo.save(customer);
		}
		return customer;
	}

	@Override
	public Customer getCustomer(int cid) {
		Optional<Customer> opt = customerRepo.findByCid(cid);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public boolean exists(int cid) {
		return customerRepo.existsByCid(cid);
	}

	@Override
	public Customer getCustomerByName(String name) {
		Optional<Customer> opt = customerRepo.findByName(name);
		return opt.isPresent()?opt.get():null;
	}

	@Override
	public boolean existsByName(String name) {
		return customerRepo.existsByName(name);
	}

	@Override
	public Customer getCustomerMyMbno(String mbno) {
		Optional<Customer> opt = customerRepo.findByMbno(mbno);
		return opt.isPresent()?opt.get():null;
	}

}