package com.dxc.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.customer.model.Biller;
import com.dxc.customer.service.BillerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class BillerController {
	@Autowired
	BillerService service;
	ResponseEntity response;
	boolean flag;
	@PostMapping("/addBiller")
	public ResponseEntity<?>addCustomer(@RequestBody Biller biller){
		flag=service.addbiller(biller);
		if(flag) {
			response=new ResponseEntity<String>("biller object created",HttpStatus.CREATED);
		}
		else {
			response=new ResponseEntity<String>("biller object failure",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/getBiller")
	public ResponseEntity<?>getBiller(){
		List<Biller>billerList=service.getAllBiller();
		if(billerList!=null) {
			response = new ResponseEntity<List<Biller>>(billerList,HttpStatus.OK);
		}
		else {
			response=new ResponseEntity<String>("failure",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
