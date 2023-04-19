package com.dxc.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.customer.model.Customer;

@Repository
@Transactional
public interface CustomerDao extends JpaRepository<Customer, String>{

}
