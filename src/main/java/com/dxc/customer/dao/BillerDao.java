package com.dxc.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.customer.model.Biller;



public interface BillerDao extends JpaRepository<Biller, String>{

}
