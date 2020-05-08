package com.cg.obs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.obs.model.LoginCredentials;

public interface LoginJpaRepo extends JpaRepository<LoginCredentials, Integer> {

}
