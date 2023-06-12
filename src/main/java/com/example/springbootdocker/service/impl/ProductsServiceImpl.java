package com.example.springbootdocker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootdocker.entity.Products;
import com.example.springbootdocker.repository.ProductsRepo;
import com.example.springbootdocker.service.ProductsService;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	private ProductsRepo repo;

	@Override
	public List<Products> findAll() {
		return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
	}

	@Override
	public Products findById(Long id) {
		Optional<Products> result = repo.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	@Override
	public Products findBySlug(String slug) {
		return repo.findBySlug(slug);
	}

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateQuantity(Integer newQuantity, Long id) {
		repo.updateQuantity(newQuantity, id);
	}
}
