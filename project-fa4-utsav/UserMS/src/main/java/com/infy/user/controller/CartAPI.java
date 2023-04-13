package com.infy.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infy.user.dto.CartDTO;
import com.infy.user.dto.ProductDTO;
import com.infy.user.exception.UserMsException;
import com.infy.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/cart")
public class CartAPI {
	@Autowired
	private UserService userService;
	
	@Value("${product.uri}")
	String prodUri;

	@GetMapping(value = "/get/{buyerId}")
	public ResponseEntity<List<CartDTO>> getProductListFromCart(@PathVariable String buyerId) throws UserMsException {

		try {
			List<CartDTO> list = userService.getCartProducts(buyerId);

			return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
		} catch (UserMsException e) {
			System.out.println(e.getMessage());
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);

		}
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<String> addProductToCart(@RequestBody CartDTO cartDTO) throws UserMsException
	{
		try {	
		 
		ProductDTO product = new RestTemplate().getForObject(prodUri+"/prodMS/getById/"+cartDTO.getProdId(), ProductDTO.class);
		System.out.println(product);
		System.out.println(product instanceof ProductDTO);
		String msg = userService.cartService(product.getProdId(), cartDTO.getBuyerId(), cartDTO.getQuantity());
		
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
		}
		catch(Exception e)
		{
			String newMsg = "There was some error";
			if(e.getMessage().equals("404 null"))
			{
				newMsg = "There are no PRODUCTS for the given product ID";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,newMsg,e);
		}
	}

	@DeleteMapping(value = "/remove/{buyerId}/{prodId}")
	public ResponseEntity<String> removeFromCart(@PathVariable String buyerId, @PathVariable String prodId)
			throws UserMsException {

		// ProductDTO product = new
		// RestTemplate().getForObject("http://localhost:8100/prodMs/getByName/"+prodName,
		// ProductDTO.class);
		try {
			String msg = userService.removeFromCart(buyerId, prodId);

			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (UserMsException e) {
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);

		}
	}

}
