package org.id.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHK")
public class Checking extends Account {
	private double overdraft;

	public Checking() {
		super();
	}

	public Checking(String acountCode, Date creationDate, double balance, Client client, double overdraft) {
		super(acountCode, creationDate, balance, client);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

}
