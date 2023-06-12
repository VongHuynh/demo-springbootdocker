package com.example.springbootdocker.service;

import com.example.springbootdocker.dto.CartDetailDto;

public interface OrderDetailsService {

	void insert(CartDetailDto cartDetailDto);
}
