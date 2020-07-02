package org.id.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("Paymt")
public class Payment extends Operation{
	

	public Payment() {
		super();
	}

	public Payment(Date operationDate, double amount, Account account) {
		super(operationDate, amount, account);
	}
	

}
