package com.edureka.studentms;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Payment {
	
	@Id
	private Integer id;
	private Integer studentId;
	private long amount;
	
	public Payment() {
		
	}
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "{ \"studentId\":" + studentId + ", \"amount\":" + amount + "}";
	}

	
}
