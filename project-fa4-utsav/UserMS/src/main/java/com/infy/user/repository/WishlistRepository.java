package com.infy.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.user.entity.Wishlist;
import com.infy.user.utility.CustomPK;


public interface WishlistRepository extends CrudRepository<Wishlist, CustomPK> {

}