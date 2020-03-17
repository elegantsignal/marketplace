package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.service.IUserAccountService;
import by.itacademy.elegantsignal.marketplace.web.dto.UserAccountDTO;


@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IUserAccount apply(final UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		return entity;
	}
}
