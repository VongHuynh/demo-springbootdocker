package com.example.springbootdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdocker.entity.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
