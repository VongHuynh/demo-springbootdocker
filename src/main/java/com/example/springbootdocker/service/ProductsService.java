package com.example.springbootdocker.service;

import java.util.List;

import com.example.springbootdocker.entity.Products;

public interface ProductsService {

	List<Products> findAll();
	Products findById(Long id);
	Products findBySlug(String slug);
	void updateQuantity(Integer newQuantity, Long id);
}
