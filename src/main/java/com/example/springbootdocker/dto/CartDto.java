package com.example.springbootdocker.dto;

import java.io.Serializable;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto implements Serializable {

	private static final long serialVersionUID = -3816551819932131145L;
	
	private Long orderId;
	private String address;
	private String phone;
	private Double totalPrice = 0D;
	private Integer totalQuantity = 0;
	private HashMap<Long, CartDetailDto> details = new HashMap<>(); 
}
