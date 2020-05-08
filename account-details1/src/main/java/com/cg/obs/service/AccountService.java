/**
 * 
 */
package com.cg.obs.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.cg.obs.model.Account;

/**
 * @author sohel
 *
 */
@Service
public interface AccountService extends JpaRepository<Account, String>{
	public List<Account> getAccountsByUserId(String userId);
}
