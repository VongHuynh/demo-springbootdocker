package com.example.springbootdocker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootdocker.dto.CartDetailDto;
import com.example.springbootdocker.repository.OrderDetailsRepo;
import com.example.springbootdocker.service.OrderDetailsService;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepo repo;

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void insert(CartDetailDto cartDetailDto) {
		repo.insert(cartDetailDto);
	}
}
