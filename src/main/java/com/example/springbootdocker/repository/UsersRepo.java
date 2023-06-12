package com.example.springbootdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdocker.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

	Users findByUsername(String username);
}
