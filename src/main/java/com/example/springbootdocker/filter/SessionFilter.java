package com.example.springbootdocker.filter;

import java.io.IOException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.example.springbootdocker.constant.SessionConstant;
import com.example.springbootdocker.dto.CartDto;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		validateCart(session);
		chain.doFilter(request, response);
	}
	
	private void validateCart(HttpSession session) {
		if (ObjectUtils.isEmpty(session.getAttribute(SessionConstant.CURRENT_CART))) {
			session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
		}
	}
}
