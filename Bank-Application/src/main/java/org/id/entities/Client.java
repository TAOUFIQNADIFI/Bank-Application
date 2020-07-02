package org.id.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity

public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long clientCode;
	private String clientName;
	private String clientEAddress;
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Account> accounts;

	public Client() {
		super();
	}

	public Client(String clientName, String clientEAddress) {
		super();
		this.clientName = clientName;
		this.clientEAddress = clientEAddress;
	}

	public Long getClientCode() {
		return clientCode;
	}

	public void setClientCode(Long clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientEAddress() {
		return clientEAddress;
	}

	public void setClientEAddress(String clientEAddress) {
		this.clientEAddress = clientEAddress;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

}
