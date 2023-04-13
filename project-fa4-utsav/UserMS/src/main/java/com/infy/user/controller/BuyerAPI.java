package com.infy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.user.dto.BuyerDTO;
import com.infy.user.exception.UserMsException;
import com.infy.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/buyer")
public class BuyerAPI {
	@Autowired
	private UserService userService;

	@Autowired
	Environment environment;

	/*
	 * Buyer can get its own information
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<BuyerDTO> getBuyerDetail(@PathVariable String id) {
		try {
			BuyerDTO dto = userService.getBuyer(id);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (UserMsException e) {
			e.printStackTrace();
			String msg = "User not found";
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteBuyerAccount(@PathVariable String id) {

		String msg;
		try {
			msg = userService.deleteBuyer(id);

			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (UserMsException e) {

			e.printStackTrace();

			msg = "Cannot delete user";
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}

	}

	@PutMapping(value = "/deactivate/{id}")
	public ResponseEntity<String> deactivateBuyerAccount(@PathVariable String id) {
		String msg;
		try {
			msg = userService.deactivateBuyer(id);

			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (UserMsException e) {

			e.printStackTrace();

			msg = "Cannot deactivate user";
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}

	}

}
