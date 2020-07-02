package org.id.service;

import java.util.Date;
import java.util.Optional;

import org.id.dao.AccountRepository;
import org.id.dao.OperationRepository;
import org.id.entities.Account;
import org.id.entities.Checking;
import org.id.entities.Deposit;
import org.id.entities.Operation;
import org.id.entities.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class bankService implements IBankService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Account checkAccount(String account) {
		// TODO Auto-generated method stub
		Optional<Account> ac = accountRepository.findById(account);
		if (ac == null)
			throw new RuntimeException("Account Not Found");

		return ac.get();
	}

	@Override
	public void cashDeposit(String accountCode, double amount) {
		// TODO Auto-generated method stub

		Account cp = checkAccount(accountCode);

		Deposit d = new Deposit(new Date(), amount, cp);
		operationRepository.save(d);
		cp.setBalance(cp.getBalance() + amount);
		accountRepository.save(cp);

	}

	@Override
	public void withrawMoney(String accountCode, double amount) {
		// TODO Auto-generated method stub
		double overdraft = 0;
		Account cp = checkAccount(accountCode);
		if (cp instanceof Checking)
			overdraft = ((Checking) cp).getOverdraft();
		if (cp.getBalance() + overdraft < amount)
			throw new RuntimeException("insufficient balance");

		Withdrawal w = new Withdrawal(new Date(), amount, cp);
		operationRepository.save(w);
		cp.setBalance(cp.getBalance() - amount);
		accountRepository.save(cp);

	}

	@Override
	public void transfer(String account1Code, String account2Code, double amount) {
		// TODO Auto-generated method stub
		System.out.println("test ibak service...=" + account1Code);
		System.out.println("test ibak service...=" + account2Code);

		if (account1Code.equals(account2Code)) {
			System.out.println("test ibak service...=" + "trueeeee");

			throw new RuntimeException("operation Not allowed");
		} else {
			System.out.println("test ibak service...=" + "falsssseeeeeee");

			withrawMoney(account1Code, amount);
			cashDeposit(account2Code, amount);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Operation> operationsList(String accountCode, int page, int size) {
		// TODO Auto-generated method stub

		return operationRepository.listOperation(accountCode,
				new PageRequest(page, size, Sort.by(Order.desc("opNumbre"))));
		// return null;
	}

}
