package com.infy.user.service;

import java.util.List;

import com.infy.user.dto.BuyerDTO;
import com.infy.user.dto.CartDTO;
import com.infy.user.dto.SellerDTO;
import com.infy.user.exception.UserMsException;

public interface UserService {

	public String buyerRegistration(BuyerDTO buyerDTO) throws UserMsException;
	
	public String sellerRegistration(SellerDTO sellerDTO) throws UserMsException;
	
	public String buyerLogin(String email, String password) throws UserMsException;
	
	public String sellerLogin(String email, String password) throws UserMsException;
	
	public String deleteBuyer(String id);
	
	public String deleteSeller(String id);
	
	public String wishlistService(String prodId,String buyerId);
	public String removeFromWishlist(String prodId,String buyerId);
	
	public String cartService(String prodId, String buyerId, Integer quantity);
	
	public List<CartDTO> getCartProducts(String id) throws UserMsException;
	
	public String removeFromCart(String buyerId, String prodId) throws UserMsException;
	
	public String updateRewardPoint(String buyerId, Integer rewPoints) throws UserMsException;
	
}
