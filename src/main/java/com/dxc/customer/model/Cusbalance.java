package com.dxc.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cbalance")
public class Cusbalance {

	@Id
	private String accno;
	private String amount ;
	private String phone;
	public Cusbalance() {
		
	}
	public Cusbalance(String accno, String amount, String phone) {
		super();
		this.accno = accno;
		this.amount = amount;
		this.phone = phone;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Cusbalance [accno=" + accno + ", amount=" + amount + ", phone=" + phone + "]";
	}

	
	
	
	
	
	
}
