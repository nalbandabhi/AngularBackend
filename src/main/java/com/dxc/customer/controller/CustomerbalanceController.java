package com.dxc.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.customer.model.Cusbalance;
import com.dxc.customer.service.CustomBalService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerbalanceController {
	@Autowired
	CustomBalService service;
	ResponseEntity response;
	boolean flag;
	@PostMapping("/addCbalance")
	public ResponseEntity<?>addCustbalance(@RequestBody Cusbalance cusbalance){
		flag=service.addcustomer(cusbalance);
		if(flag) {
			response=new ResponseEntity<String>("customer object created",HttpStatus.CREATED);
		}
		else {
			response=new ResponseEntity<String>("customer object failure",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/getCbalance")
	public ResponseEntity<?>getCustbalance(){
		List<Cusbalance>customerList=service.getAllCustomerbalance();
		if(customerList!=null) {
			response = new ResponseEntity<List<Cusbalance>>(customerList,HttpStatus.OK);
		}
		else {
			response=new ResponseEntity<String>("failure",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
//	@GetMapping("/getCustomer/{phone}")
//    public ResponseEntity<?> Cusbal(@PathVariable int phone) {
//		Optional<Cusbalance>custbalList = service.balUser(phone);
//        if (custbalList != null) {
//            return new ResponseEntity<Optional<Cusbalance>>(custbalList,HttpStatus.OK);
//        } else {
//            return new ResponseEntity<String>("failure",HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping("/getCustomer/{phone}")
    public ResponseEntity<?> getUserById(@PathVariable String phone) {
        Optional<Cusbalance> optionalUser = service.balUser(phone);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User object with phone " + phone + " does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
