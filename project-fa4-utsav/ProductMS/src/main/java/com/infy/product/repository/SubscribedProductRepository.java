package com.infy.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.product.entity.SubscribedProduct;
import com.infy.product.utility.CustomPK;

public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct, CustomPK> {
	
	

}
