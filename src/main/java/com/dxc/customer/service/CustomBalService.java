package com.dxc.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.customer.dao.CusBalanceDao;
import com.dxc.customer.model.Cusbalance;
import com.dxc.customer.model.Customer;


@Service
public class CustomBalService {
	@Autowired
	CusBalanceDao dao;
	public boolean addcustomer(Cusbalance c) {
		Cusbalance cusbalance=dao.save(c);
		if(cusbalance!=null) {
			return true;
		}
		return false;
	}

	
	public List<Cusbalance>getAllCustomerbalance(){
		return dao.findAll();
	}
	
	public Optional<Cusbalance> balUser(String phone) {
        Optional<Cusbalance> optionalemail = dao.findById(phone);
        if (optionalemail.isPresent()) {
            
            return optionalemail;
        }
        return null;
    }
}
