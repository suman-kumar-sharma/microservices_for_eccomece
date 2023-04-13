package com.infy.user.dto;

import com.infy.user.entity.Wishlist;

public class WishlistDTO {
	
	private String buyerId;
	private String prodId;
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	public Wishlist toEntity() {
		Wishlist entity = new Wishlist();
		entity.getCustomId().setBuyerId(buyerId);
		entity.getCustomId().setProdId(prodId);
		return entity;
	}

}
