package com.example.springbootdocker.service.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springbootdocker.constant.RolesConstant;
import com.example.springbootdocker.entity.Roles;
import com.example.springbootdocker.entity.Users;
import com.example.springbootdocker.repository.UsersRepo;
import com.example.springbootdocker.service.RolesService;
import com.example.springbootdocker.service.UsersService;

import jakarta.transaction.Transactional;

@Service
public class UsersServiceImpl implements UsersService {
	
	private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;
	
	@Autowired
	private RolesService rolesService;

	@Override
	public Users doLogin(Users userRequest) {
		Users userResponse = repo.findByUsername(userRequest.getUsername());
		
		if (ObjectUtils.isNotEmpty(userResponse)) {
			boolean checkPassword = bcrypt.matches(userRequest.getHashPassword(), userResponse.getHashPassword());
			return checkPassword ? userResponse : null;
		}
		
		return null;
	}

	@Override
	@Transactional(rollbackOn = {Throwable.class})
	public Users save(Users user) throws SQLException {
		Roles role = rolesService.findByDescription(RolesConstant.USER);
		user.setRole(role);
		user.setIsDeleted(Boolean.FALSE);
		
		String hashPassword = bcrypt.encode(user.getHashPassword());
		user.setHashPassword(hashPassword);
		
		return repo.saveAndFlush(user);
	}
}
