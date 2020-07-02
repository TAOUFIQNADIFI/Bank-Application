package org.id.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP",
discriminatorType=DiscriminatorType.STRING,length=100)
public class Operation  implements Serializable{
	@Id @GeneratedValue
	private Long opNumbre;
	private Date operationDate;
	private double amount;
	@ManyToOne
	@JoinColumn(name="CODE_CPTE" )
	private Account account;
	
	
	
	public Operation() {
		super();
	}
	
	
	public Operation( Date operationDate, double amount, Account account) {
		super();
		this.operationDate = operationDate;
		this.amount = amount;
		this.account = account;
	}


	public Long getOpNumbre() {
		return opNumbre;
	}
	public void setOpNumbre(Long opNumbre) {
		this.opNumbre = opNumbre;
	}
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
