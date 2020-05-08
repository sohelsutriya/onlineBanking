/**
 * 
 */
package com.cg.obs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sohel
 *
 */
@Entity
@Table(name = "Account")
public class Account {
	@Id
	private String accountNo;
	@Column
	private String accountType;
	@Column
	private double balance;
	@Column
	private String accountStatus;
	@Column
	private Date openingDate;
	@Column
	private String userId;
	@Column
	private String remarks;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String accountNo, String accountType, double balance, String accountStatus, Date openingDate,
			String userId, String remarks) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.balance = balance;
		this.accountStatus = accountStatus;
		this.openingDate = openingDate;
		this.userId = userId;
		this.remarks = remarks;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountType=" + accountType + ", balance=" + balance
				+ ", accountStatus=" + accountStatus + ", openingDate=" + openingDate + ", userId=" + userId
				+ ", remarks=" + remarks + "]";
	}

}
