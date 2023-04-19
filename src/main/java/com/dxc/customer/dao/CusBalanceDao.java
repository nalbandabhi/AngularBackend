package com.dxc.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.customer.model.Cusbalance;

@Repository
@Transactional
public interface CusBalanceDao extends JpaRepository<Cusbalance, String>{

}
