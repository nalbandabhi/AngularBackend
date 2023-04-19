package com.dxc.customer.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.customer.model.Customer;
import com.dxc.customer.service.CustomerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {
	@Autowired
	CustomerService service;
	ResponseEntity response;
	String jwtToken;
	boolean flag;
	@PostMapping("/addCustomer")
	public ResponseEntity<?>addCustomer(@RequestBody Customer customer){
		flag=service.addcustomer(customer);
		if(flag) {
			response=new ResponseEntity<String>("customer object created",HttpStatus.CREATED);
		}
		else {
			response=new ResponseEntity<String>("customer object failure",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/getCustomer")
	public ResponseEntity<?>getCustomer(){
		List<Customer>customerList=service.getAllCustomer();
		if(customerList!=null) {
			response = new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
		}
		else {
			response=new ResponseEntity<String>("failure",HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@DeleteMapping("/getCustomer/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        boolean isDeleted = service.deleteUsers(email);
        if (isDeleted) {
            return new ResponseEntity<>("User object with email " +email + " has been deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User object with email " + email + " does not exist", HttpStatus.NOT_FOUND);
        }
    }

	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody Customer customer, HttpSession session){
		flag = service.validate(customer);
		if (flag) {
			session.setAttribute("email",customer.getEmail());
			jwtToken = generateToken((customer.getEmail()));
			response = new ResponseEntity<String>(jwtToken, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<String>("Falied ", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?>logout(HttpSession session){
		
		if ((session != null) && (session.getAttribute("email") != null)) {
			session.invalidate();
			response = new ResponseEntity<String>("Logout Success", HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<String>("Falied ", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	public String generateToken(String email) {
		String token = Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+200000))
					   .signWith(SignatureAlgorithm.HS256, "secretKey").compact();
		System.out.println("Token: "+token);
		return token;
	}
}