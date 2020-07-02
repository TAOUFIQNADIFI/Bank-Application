package org.id.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEP")
public class Deposit extends Operation {

	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deposit(Date operationDate, double amount, Account account) {
		super(operationDate, amount, account);
		// TODO Auto-generated constructor stub
	}

}
