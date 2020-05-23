package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.service.Account;
import by.itacademy.elegantsignal.marketplace.service.IAccountService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import by.itacademy.elegantsignal.marketplace.web.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class AccountFromDTOConverter implements Function<AccountDTO, Account> {

	@Autowired IAccountService accountService;
	@Autowired IUserService userService;

	@Override public Account apply(final AccountDTO accountDTO) {
		return accountService
			.createEntity()
			.setUser(userService.get(accountDTO.getUserId()))
			.setBalance(accountDTO.getBalance())
			.setWithdrawalAmount(accountDTO.getWithdrawalAmount())
			;
	}
}
