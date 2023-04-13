package com.infy.user.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.infy.user.dto.WishlistDTO;
import com.infy.user.utility.CustomPK;

@Entity
@Table(name = "wishlist")
public class Wishlist {
	
	@EmbeddedId
	private CustomPK customId;

	public CustomPK getCustomId() {
		return customId;
	}

	public void setCustomId(CustomPK customId) {
		this.customId = customId;
	}
	
	public WishlistDTO toDTO() {
		WishlistDTO dto = new WishlistDTO();
		
		dto.setBuyerId(customId.getBuyerId());
		dto.setProdId(customId.getProdId());
		
		return dto;
	}

}
