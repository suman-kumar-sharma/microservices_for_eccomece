package com.infy.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infy.user.dto.BuyerDTO;

@Entity
@Table(name = "buyer")
public class Buyer {
	
	@Id
	private String buyerId;
	private String name;
	private String email;
	private Long phoneNumber;
	private String password;
	private Boolean isPrivileged;
	private Integer rewardPoints;
	private Boolean isActive;
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(Boolean isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public BuyerDTO convertToDTO() {
		BuyerDTO dto = new BuyerDTO();
		dto.setEmail(email);
		dto.setIsActive(isActive);
		dto.setIsPrivileged(isPrivileged);
		dto.setName(name);
		dto.setPassword(password);
		dto.setPhoneNumber(phoneNumber);
		dto.setRewardPoints(rewardPoints);
		return dto;
	}
	

}
