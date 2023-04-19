package com.dxc.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.customer.dao.CustomerDao;
import com.dxc.customer.model.Customer;

@Service
public class CustomerService {
	@Autowired
	CustomerDao dao;
	public boolean addcustomer(Customer c) {
		Customer customer=dao.save(c);
		if(customer!=null) {
			return true;
		}
		return false;
	}
	public boolean validate(Customer customer) {
		Optional<Customer> optCustomer=dao.findById(customer.getEmail());
		
		if(optCustomer.isPresent()&& optCustomer.get().getPassword().equals(customer.getPassword())) {
			return true;
		}
		return false;
	}
	
	public List<Customer>getAllCustomer(){
		return dao.findAll();
	}
	
	public boolean deleteUsers(String email) {
        Optional<Customer> optionalemail = dao.findById(email);
        if (optionalemail.isPresent()) {
            dao.delete(optionalemail.get());
            return true;
        }
        return false;
    }
	
}

