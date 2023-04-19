package com.dxc.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.customer.dao.BillerDao;
import com.dxc.customer.model.Biller;

@Service
public class BillerService {
	@Autowired
	BillerDao billerdao;
	public boolean addbiller(Biller b) {
		Biller biller=billerdao.save(b);
		if(biller!=null) {
			return true;
		}
		return false;
	}
	public boolean validate(Biller biller) {
		Optional<Biller> optBiller=billerdao.findById(biller.getFirstname());
		
		if(optBiller.isPresent()&& optBiller.get().getPassword().equals(biller.getPassword())) {
			return true;
		}
		return false;
	}
	
	public List<Biller>getAllBiller(){
		return billerdao.findAll();
	}
}
