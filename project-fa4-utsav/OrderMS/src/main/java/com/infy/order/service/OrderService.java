package com.infy.order.service;

import java.util.List;

import com.infy.order.dto.CartDTO;
import com.infy.order.dto.OrderDTO;
import com.infy.order.dto.OrderPlacedDTO;
import com.infy.order.dto.ProductDTO;
import com.infy.order.exception.OrderMsException;

public interface OrderService {
	
	public List<OrderDTO> viewAllOrders() throws OrderMsException;

	public OrderPlacedDTO placeOrder(List<ProductDTO> productList, List<CartDTO> cartList, OrderDTO order) throws OrderMsException;

	public List<OrderDTO> viewOrdersByBuyer(String buyerId)throws OrderMsException;

	public OrderDTO viewOrder(String orderId) throws OrderMsException;

	public String reOrder(String buyerId, String orderId) throws OrderMsException;


}
