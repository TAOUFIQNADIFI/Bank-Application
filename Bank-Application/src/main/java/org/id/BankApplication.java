package org.id;

import java.util.Date;

import org.id.dao.AccountRepository;
import org.id.dao.ClientRepository;
import org.id.dao.OperationRepository;
import org.id.entities.Account;
import org.id.entities.Checking;
import org.id.entities.Client;
import org.id.entities.Operation;
import org.id.entities.Payment;
import org.id.entities.Saving;
import org.id.entities.Withdrawal;
import org.id.service.IBankService;
import org.id.service.bankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication implements CommandLineRunner {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBankService bankservice;

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// TODO Auto-generated method stub

		/*
		 * Client Cl1 = clientRepository.save(new Client("James Must",
		 * "h.n@gmail.com")); Account Ac1 = accountRepository.save(new Saving("ac1", new
		 * Date(), 2000, Cl1, 5.5));
		 * 
		 * Client Cl2 = clientRepository.save(new Client("Peter Must",
		 * "h.n@gmail.com")); Account Ac2 = accountRepository.save(new Saving("ac2", new
		 * Date(), 4000, Cl2, 5.5));
		 * 
		 * Client Cl3 = clientRepository.save(new Client("James Must",
		 * "h.n@gmail.com")); Account Ac3 = accountRepository.save(new Saving("ac3", new
		 * Date(), 5500, Cl3, 5.5));
		 */
		/*
		 * Client defaultValue = clientRepository.save(new
		 * Client("DefaultValue","df@defaultValue.com"));
		 * 
		 * Account defaultVal = accountRepository.save(new Saving("defaultValue",new
		 * Date(),0000,defaultValue,5.5));
		 */

		/*
		 * Account Ac2 = accountRepository.save(new Checking("c2",new
		 * Date(),9000,c1,6000));
		 * 
		 * Client c1 = clientRepository.save(new Client("hassan","h.n@gmail.com"));
		 * Client c2 = clientRepository.save(new Client("hassan6","h.n@gmail.com"));
		 * 
		 * 
		 * Account Ac1 = accountRepository.save(new Saving("c1",new
		 * Date(),9000,c2,5.5)); Account Ac2 = accountRepository.save(new
		 * Checking("c2",new Date(),9000,c1,6000));
		 * 
		 * Operation op1 = operationRepository.save(new Payment(new Date(),1000,Ac1));
		 * Operation op2 = operationRepository.save(new Withdrawal(new
		 * Date(),1000,Ac1)); Operation op3 = operationRepository.save(new Payment(new
		 * Date(),1000,Ac2)); Operation op4 = operationRepository.save(new
		 * Withdrawal(new Date(),1000,Ac2)); Operation op5 =
		 * operationRepository.save(new Payment(new Date(),100,Ac1)); Operation op6 =
		 * operationRepository.save(new Withdrawal(new Date(),100,Ac2));
		 * 
		 * bankservice.cashDeposit("c1", 111111);
		 */

	}

}
