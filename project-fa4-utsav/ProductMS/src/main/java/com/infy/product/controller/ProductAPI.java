package com.infy.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.product.dto.ProductDTO;
import com.infy.product.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductAPI {

	@Autowired
	private ProductService productService;

	@Autowired
	private Environment environment;

	@GetMapping(value = "")
	public ResponseEntity<List<ProductDTO>> viewAllProducts() {
		try {
			List<ProductDTO> list = productService.viewAllProducts();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO prod) {

		try {
			String msg = productService.addProduct(prod);
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(environment.getProperty(e.getMessage()), HttpStatus.UNAUTHORIZED);
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> getByProductId(@PathVariable String id) {
		try {
			ProductDTO productDTO = productService.getProductById(id);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value = "/name/{name}")
	public ResponseEntity<ProductDTO> getByProductName(@PathVariable String name) {
	
		try {
			ProductDTO productDTO = productService.getProductByName(name);
			return new ResponseEntity<>(productDTO, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()), e);
		}
	}

	@GetMapping(value = "/category/{category}")
	public ResponseEntity<List<ProductDTO>> getByProductCategory(@PathVariable String category) {
		
		try {
			List<ProductDTO> productDTO = productService.getProductByCategory(category);
			return new ResponseEntity<List<ProductDTO>>(productDTO, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()), e);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable String id) {

		try {
			String msg = productService.deleteProduct(id);
			return new ResponseEntity<>(msg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(environment.getProperty(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/stock/{prodId}/{quantity}")
	public ResponseEntity<Boolean> updateStock(@PathVariable String prodId, @PathVariable Integer quantity) {
		try {
			Boolean status = productService.updateStockOfProd(prodId, quantity);
			return new ResponseEntity<>(status, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, environment.getProperty(e.getMessage()), e);
		}
	}

}

/*
 * 1. GET api/products 
 * 2. POST api/products @RequestBody ProductDTO 
 * 3. GET api/products/{id} 
 * 4. GET api/products/name/{name} 
 * 5. GET api/products/category/{category} 
 * 6. DELETE api/products/{id} 
 * 7. PUT api/products/stock/{prodId}/{quantity}
 */
