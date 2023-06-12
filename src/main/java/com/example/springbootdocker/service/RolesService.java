package com.example.springbootdocker.service;

import com.example.springbootdocker.entity.Roles;

public interface RolesService {
	
	Roles findByDescription(String description);
}
