package com.example.springbootdocker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdocker.constant.SessionConstant;
import com.example.springbootdocker.dto.CartDto;
import com.example.springbootdocker.entity.Users;
import com.example.springbootdocker.service.CartService;
import com.example.springbootdocker.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
	
	@Autowired
	private CartService cartService;

	@GetMapping("/update")
	public ResponseEntity<?> doGetUpdate(@RequestParam("productId") Long producId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, producId, quantity, isReplace);
		return ResponseEntity.ok(currentCart);
	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefreshData(HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		return ResponseEntity.ok(currentCart);
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<?> doGetCheckout(@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			HttpSession session) {
		Users currentUser = SessionUtil.getCurrentUser(session);
		
		if (ObjectUtils.isEmpty(currentUser)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
		}
		
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		try {
			cartService.insert(currentCart, currentUser, address, phone);
			session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
		}
	}
}
