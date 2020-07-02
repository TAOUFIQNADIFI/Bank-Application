package org.id.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_ACCT",
discriminatorType=DiscriminatorType.STRING,length=4)
public class Account implements Serializable{
	@Id
	private String accountCode;
	private Date creationDate;
	   private double Balance;
	   @ManyToOne
	   @JoinColumn(name="CODE_CLI")
	   private Client client;
	   @OneToMany(mappedBy="account")
	   private Collection<Operation> operations;
	   
	   
	   
	   
	public Account() {
		super();
	}
	
	
	public Account(String acountCode, Date creationDate, double balance, Client client) {
		super();
		accountCode = acountCode;
		this.creationDate = creationDate;
		Balance = balance;
		this.client = client;
	}


	


	public Collection<Operation> getOperations() {
		return operations;
	}


	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}


	public String getAccountCode() {
		return accountCode;
	}
	public void setAcountCode(String acountCode) {
		accountCode = acountCode;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	   
	   

}
