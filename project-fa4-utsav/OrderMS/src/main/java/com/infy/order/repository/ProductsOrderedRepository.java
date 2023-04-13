package com.infy.order.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.order.entity.ProductsOrdered;
import com.infy.order.utility.CustomPK;

public interface ProductsOrderedRepository extends CrudRepository<ProductsOrdered, CustomPK>{

}
