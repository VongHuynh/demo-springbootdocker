package com.example.springbootdocker.service;

import com.example.springbootdocker.dto.CartDto;
import com.example.springbootdocker.entity.Users;

public interface CartService {

	CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);
	Integer getTotalQuantity(CartDto cart);
	Double getTotalPrice(CartDto cart);
	void insert(CartDto cartDto, Users user, String address, String phone) throws Exception;
}
