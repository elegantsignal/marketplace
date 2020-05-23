package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.service.Account;
import by.itacademy.elegantsignal.marketplace.web.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountToDTOConverter implements Function<Account, AccountDTO> {

	@Override public AccountDTO apply(final Account account) {
		return new AccountDTO()
			.setUserId(account.getUser().getId())
			.setBalance(account.getBalance());
	}
}
