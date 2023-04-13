package com.infy.user.dto;

import com.infy.user.entity.Buyer;

public class BuyerDTO {

	private String name;
	private String email;
	private Long phoneNumber;
	private String password;
	private Boolean isPrivileged;
	private Integer rewardPoints;
	private Boolean isActive;

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

	public Buyer convertToEntity(String id) {
		Buyer buyer = new Buyer();
		buyer.setBuyerId(id);
		buyer.setEmail(email);
		buyer.setIsActive(isActive);
		buyer.setIsPrivileged(isPrivileged);
		buyer.setName(name);
		buyer.setPassword(password);
		buyer.setPhoneNumber(phoneNumber);
		buyer.setRewardPoints(rewardPoints);
		return buyer;
	}
	
	public Buyer convertToEntity() {
		//without Id 
		//Id should be given explicitly
		Buyer buyer = new Buyer();
		buyer.setEmail(email);
		buyer.setIsActive(isActive);
		buyer.setIsPrivileged(isPrivileged);
		buyer.setName(name);
		buyer.setPassword(password);
		buyer.setPhoneNumber(phoneNumber);
		buyer.setRewardPoints(rewardPoints);
		return buyer;
	}

}
