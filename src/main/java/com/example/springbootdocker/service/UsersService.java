package com.example.springbootdocker.service;

import java.sql.SQLException;

import com.example.springbootdocker.entity.Users;

public interface UsersService {

	Users doLogin(Users userRequest);
	Users save(Users user) throws SQLException;
}
