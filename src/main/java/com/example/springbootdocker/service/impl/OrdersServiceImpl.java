package com.example.springbootdocker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootdocker.entity.Orders;
import com.example.springbootdocker.repository.OrdersRepo;
import com.example.springbootdocker.service.OrdersService;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersRepo repo;

	@Transactional(value = TxType.REQUIRED)
	@Override
	public Orders insert(Orders order) {
		return repo.saveAndFlush(order);
	}
}
