package com.example.springbootdocker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootdocker.entity.Roles;
import com.example.springbootdocker.repository.RolesRepo;
import com.example.springbootdocker.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepo repo;

	@Override
	public Roles findByDescription(String description) {
		return repo.findByDescription(description);
	}
}
