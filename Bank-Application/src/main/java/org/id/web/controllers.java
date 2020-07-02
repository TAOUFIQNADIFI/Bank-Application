package org.id.web;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.id.dao.OperationRepository;
import org.id.entities.Account;
import org.id.entities.Operation;

import org.id.entities.Checking;
import org.id.entities.Deposit;
import org.id.entities.Saving;
import org.id.entities.Withdrawal;
import org.id.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.SessionScope;

@SessionAttributes("account")
@Controller
public class controllers {
	@Autowired
	private IBankService bankService;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String clientInf(Principal user, Model model, String AccountCode, HttpServletRequest request,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "12") int size) {
		Account ac0 = (Account) request.getSession().getAttribute("account");
		AccountCode = ac0.getAccountCode();

		if (user == null) {
			return "login";
		} else if ((ac0 == null) || (AccountCode == "0000")) {

			return "redirect:/checkAccount";
		}

		else {

			try {

				ac0 = (Account) request.getSession().getAttribute("account");

				Page<Operation> operationsPage = bankService.operationsList(AccountCode, page, size);
				System.out.println("##### operations size is:   " + operationsPage.getContent().size());
				model.addAttribute("operationsList", operationsPage.getContent());
				int[] pages = new int[operationsPage.getTotalPages()];
				model.addAttribute("pages", pages);
				String type = null;
				if (ac0 instanceof Checking) {
					type = "checking";
				}
				if (ac0 instanceof Saving) {
					type = "saving";
				}
				model.addAttribute("operationsList", operationsPage.getContent());

				model.addAttribute("type", type);
				model.addAttribute("account1", false);
				model.addAttribute("account", ac0);
				model.addAttribute("user", user.getName());

				return "account";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("exception", e);

			}

			return "account";
		}
	}

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String accountOperation1(Model model, Principal user) {

		if (user == null) {

			return "login";

		} else {

			model.addAttribute("user", user.getName());
			model.addAttribute("account1", true);
			// model.addAttribute("account",account);
			return "client";
		}

	}

	@RequestMapping(value = "/operations", method = RequestMethod.GET)
	public String index(Model model, Principal user) {
		if (user == null) {
			return "login";
		} else {
			model.addAttribute("user", user.getName());

			model.addAttribute("account1", true);
			return "accounts";
		}
	}

	@RequestMapping("/checkAccount1")
	public String check(Principal user, Model model, String AccountCode,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "12") int size) {

		// boolean account1=true;
		model.addAttribute("account1", true);

		try {

			Account ac = bankService.checkAccount(AccountCode);

			Page<Operation> operationsPage = bankService.operationsList(AccountCode, page, size);

			model.addAttribute("operationsList", operationsPage.getContent());
			int[] pages = new int[operationsPage.getTotalPages()];
			model.addAttribute("pages", pages);
			String type = null;
			if (ac instanceof Checking) {
				type = "checking";
			}
			if (ac instanceof Saving) {
				type = "saving";
			}

			model.addAttribute("type", type);
			model.addAttribute("account1", false);
			model.addAttribute("account", ac);
			model.addAttribute("user", user.getName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("exception", e);

		}

		return "accounts";
	}

	@RequestMapping("/checkAccount")
	public String checkAccount1(Principal user, Model model, String AccountCode) {

		Account ac1 = bankService.checkAccount("0000");

		if (user == null) {

			return "login";
		}

		else {

			if (AccountCode == null) {

				model.addAttribute("account", ac1);
				model.addAttribute("user", user.getName());

				return "checkAccount";
			}

			else {
				try {

					Account ac = bankService.checkAccount(AccountCode);

					model.addAttribute("account", ac);
					model.addAttribute("user", user.getName());

					return "redirect:/client";

				}

				catch (Exception e) { // TODO Auto-generated catch block
					model.addAttribute("exception", e);

				}
			}
		}

		model.addAttribute("account", ac1);
		return "redirect:/client";

	}
	/*
	 * @ModelAttribute("account") public Account findAccount(String AccountCode) {
	 * Account account = bankService.checkAccount(AccountCode);
	 * //System.out.print("test helper method " + account.getAccountCode());
	 * 
	 * return account; }
	 */

	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String AccountCode, String operationType, String AccountCode2,
			double amount) {
		System.out.println("save operation....///..AccountCode" + AccountCode);
		System.out.println("save operation....///.. operationType" + operationType);
		System.out.println("save operation....///..AccountCode2" + AccountCode2);
		System.out.println("save operation....///..amount" + amount);

		try {
			Account ac = bankService.checkAccount(AccountCode);
			if (operationType.equals("WithdrawM")) {
				bankService.withrawMoney(AccountCode, amount);
				// operationRepository.save(new Withdrawal(operationDate, amount, ac));

			}
			if (operationType.equals("CashDep")) {
				bankService.cashDeposit(AccountCode, amount);
				// operationRepository.save(new Deposit(operationDate, amount, ac));

			}
			if (operationType.equals("Transfer")) {
				bankService.transfer(AccountCode, AccountCode2, amount);
				// Account ac2 = bankService.checkAccount(AccountCode2);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("error", e);
			return "redirect:/checkAccount?AccountCode=" + AccountCode + "&error=" + e.getMessage();

		}

		// return "redirect:/Account ?AccountCode=" + AccountCode;
		return "redirect:/account?AccountCode=" + AccountCode;

	}

}
