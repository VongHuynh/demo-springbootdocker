package com.example.springbootdocker.util;

import com.example.springbootdocker.constant.SessionConstant;
import com.example.springbootdocker.dto.CartDto;
import com.example.springbootdocker.entity.Users;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	private SessionUtil() {}
	
	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
	}
	
	public static Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute(SessionConstant.CURRENT_USER);
	}
}
