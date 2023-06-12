package com.example.springbootdocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdocker.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {
	
	Roles findByDescription(String description);
}
