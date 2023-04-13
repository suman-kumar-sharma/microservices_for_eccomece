package com.infy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.user.dto.BuyerDTO;
import com.infy.user.dto.LoginDTO;
import com.infy.user.dto.SellerDTO;
import com.infy.user.exception.UserMsException;
import com.infy.user.service.UserService;

@CrossOrigin
@RestController
public class VisitorAPI {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping(value = "/buyer/register")
	public ResponseEntity<String> registerBuyer(@RequestBody BuyerDTO buyerDto){
		
		try {
		String s ="Buyer registered successfully with buyer Id : " + userService.buyerRegistration(buyerDto);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		catch(UserMsException e)
		{
			String s = environment.getProperty(e.getMessage());
			System.out.println("Reason: "+s);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, s, e);
		}
	}
	
	@PostMapping(value = "/buyer/login")
	public ResponseEntity<String> loginBuyer(@RequestBody LoginDTO loginDTO)
	{
		try {
			String email = loginDTO.getEmail();
			String password = loginDTO.getPassword();
			String msg = userService.buyerLogin(email, password);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(UserMsException e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/seller/register")
	public ResponseEntity<String> registerSeller(@RequestBody SellerDTO sellerDto){
		
		try {
		String s ="Seller registered successfully with seller Id : "+ userService.sellerRegistration(sellerDto);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		catch(UserMsException e)
		{
			return new ResponseEntity<>(environment.getProperty(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}

	}	
	
	@PostMapping(value = "/seller/login")
	public ResponseEntity<String> loginSeller(@RequestBody LoginDTO loginDTO)
	{
		
		try {
			String email = loginDTO.getEmail();
			String password = loginDTO.getPassword();
			String msg = userService.sellerLogin(email, password);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(UserMsException e)
		{
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
}
