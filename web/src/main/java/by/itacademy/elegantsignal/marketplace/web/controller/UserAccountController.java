package by.itacademy.elegantsignal.marketplace.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserAccountFilter;
import by.itacademy.elegantsignal.marketplace.service.IUserAccountService;
import by.itacademy.elegantsignal.marketplace.web.converter.UserAccountToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.UserAccountDTO;


@Controller
@RequestMapping(value = "/users")
public class UserAccountController {

	private final IUserAccountService userService;

	private final UserAccountToDTOConverter toDtoConverter;

	@Autowired
	private UserAccountController(
			final IUserAccountService userService,
			final UserAccountToDTOConverter toDtoConverter) {
		super();
		this.userService = userService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final UserAccountFilter filter = new UserAccountFilter();

		final List<IUserAccount> entities = userService.find(filter);
		final List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		System.out.println(models);
		return new ModelAndView("users.list", models);
	}

}
