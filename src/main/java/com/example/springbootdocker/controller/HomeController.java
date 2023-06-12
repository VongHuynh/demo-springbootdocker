package com.example.springbootdocker.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springbootdocker.constant.SessionConstant;
import com.example.springbootdocker.entity.Products;
import com.example.springbootdocker.entity.Users;
import com.example.springbootdocker.service.ProductsService;
import com.example.springbootdocker.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping(value = {"/", ""})
	public String doGetRedirectIndex() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String doGetIndex(Model model) {
		List<Products> products = productsService.findAll();
		model.addAttribute("products", products);
		return "user/index";
	}
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute(SessionConstant.CURRENT_USER);
		return "redirect:/index";
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/register";
	}
	
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest, HttpSession session) {
		Users userResponse = usersService.doLogin(userRequest);
		if (ObjectUtils.isNotEmpty(userResponse)) {
			session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
			return "redirect:/index";
		}
		return "redirect:/login";
	}
	
	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") Users userRequest, HttpSession session) {
		try {
			Users userResponse = usersService.save(userRequest);
			if (ObjectUtils.isNotEmpty(userResponse)) {
				session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
				return "redirect:/index";
			} else {
				return "redirect:/register";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/register";
		}
	}
}
