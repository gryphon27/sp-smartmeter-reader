package com.vivek.tests.scottishpower.spsmartmeterreader.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SmartAccount {
	
	@Id
	private Long accountNumber;
	private String gasId;
	private String elecId;
	private Long elecSmartRead;
	private Long gasSmartRead;
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getGasId() {
		return gasId;
	}
	public void setGasId(String gasId) {
		this.gasId = gasId;
	}
	public String getElecId() {
		return elecId;
	}
	public void setElecId(String elecId) {
		this.elecId = elecId;
	}
	public Long getElecSmartRead() {
		return elecSmartRead;
	}
	public void setElecSmartRead(Long elecSmartRead) {
		this.elecSmartRead = elecSmartRead;
	}
	public Long getGasSmartRead() {
		return gasSmartRead;
	}
	public void setGasSmartRead(Long gasSmartRead) {
		this.gasSmartRead = gasSmartRead;
	}
	
	@Override
	public String toString() {
		return "SmartAccount [accountNumber=" + accountNumber + ", gasId=" + gasId + ", elecId=" + elecId
				+ ", elecSmartRead=" + elecSmartRead + ", gasSmartRead=" + gasSmartRead + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmartAccount other = (SmartAccount) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		return true;
	}
	
}
