package org.id.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("WITHDR")
public class Withdrawal extends Operation {

	
	
	public Withdrawal() {
		super();
	}



	public Withdrawal(Date operationDate, double amount, Account account) {
		super(operationDate, amount, account);
	}

}
