package com.infy.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.user.entity.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, String> {
	
	public Buyer findByPhoneNumber(String phoneNumber);
	
	public Buyer findByEmail(String email);
	
	public Buyer findByBuyerId(String id);

}
