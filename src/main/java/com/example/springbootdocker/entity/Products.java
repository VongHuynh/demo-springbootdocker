package com.example.springbootdocker.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products implements Serializable {

	private static final long serialVersionUID = 8817710984654205495L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Integer quantity;
	
	@Column(name = "price", precision = 12, scale = 3)
	private BigDecimal price;
	
	@Column
	private String imgUrl;
	
	@Column
	private String slug;
	
	@Column
	private String description;
	
	@Column
	private Boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "typeId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ProductTypes productType;
	
	@ManyToOne
	@JoinColumn(name = "unitId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private UnitTypes unitType;
}
