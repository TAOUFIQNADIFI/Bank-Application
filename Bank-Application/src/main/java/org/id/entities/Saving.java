package org.id.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("SAV")
public class Saving extends Account {

	/**
	 * 
	 */
	private double rate;
	
	

	public Saving() {
		super();
	}

	public Saving(String acountCode, Date creationDate, double balance, Client client, double rate) {
		super(acountCode, creationDate, balance, client);
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
}
