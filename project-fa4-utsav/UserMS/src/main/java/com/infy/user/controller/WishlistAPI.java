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

import com.infy.user.dto.ProductDTO;
import com.infy.user.dto.WishlistDTO;
import com.infy.user.exception.UserMsException;
import com.infy.user.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("/wishlist")
public class WishlistAPI {

	@Autowired
	private UserService userService;

	@Value("${product.uri}")
	String prodUri;
	
	
	@GetMapping("/")
	public ResponseEntity<List<WishlistDTO>> getWishlist(){
		try {
			List<WishlistDTO> list = userService.getWishlist();
			return new ResponseEntity<List<WishlistDTO>>(list, HttpStatus.FOUND);
		}
		catch(UserMsException e) {
			e.printStackTrace();
			String msg = "No Item found";
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}
	}

	@PostMapping(value = "/")
	public ResponseEntity<String> addProductToWishlist(@RequestBody WishlistDTO wishlistDTO)
			throws UserMsException {
		try {


			ProductDTO product = new RestTemplate().getForObject(prodUri + "/prodMS/getById/" + wishlistDTO.getProdId(),
					ProductDTO.class);

			String msg = userService.addWishlist(product.getProdId(), wishlistDTO.getBuyerId());

			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			System.out.println(e);
			String newMsg = "There was some error";
			if (e.getMessage().equals("404 null")) {
				newMsg = "There are no PRODUCTS for the given product ID";
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, newMsg, e);
		}
	}
	
	@DeleteMapping("/{buyerId}/{productId}")
	public ResponseEntity<String> deleteWishlist(@PathVariable String buyerId, @PathVariable String productId) {
		WishlistDTO dto = new WishlistDTO();
		dto.setBuyerId(buyerId);
		dto.setProdId(productId);
		try {
			String message = userService.deleteWishlist(dto);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch (UserMsException e) {
			
			e.printStackTrace();
			String msg = e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg, e);
		}
	}
}
