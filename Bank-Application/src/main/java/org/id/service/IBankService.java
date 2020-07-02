package org.id.service;

import org.id.entities.Account;
import org.id.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBankService {
public Account checkAccount(String account);
public void cashDeposit(String accountCode, double amount);
public void withrawMoney(String accountCode, double amount);
public void transfer(String account1Code, String account2Code, double amount);
public Page<Operation> operationsList(String accountCode, int page, int size);

}
