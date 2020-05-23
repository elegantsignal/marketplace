package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.service.Account;
import by.itacademy.elegantsignal.marketplace.service.IAccountService;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.service.ITransactionService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import by.itacademy.elegantsignal.marketplace.web.converter.AccountFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.AccountToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderItemToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.ProductToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.TransactionToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.AccountDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderItemDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.ProductDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.TransactionDTO;
import by.itacademy.elegantsignal.marketplace.web.security.ExtendedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller @RequestMapping("/user/shop")
public class UserShopPageController extends AbstractController {

	@Autowired private IProductService productService;
	@Autowired private IOrderItemService orderItemService;
	@Autowired private IUserService userService;
	@Autowired private IAccountService accountService;
	@Autowired private ITransactionService transactionService;
	@Autowired private ProductToDTOConverter productToDTOConverter;
	@Autowired private OrderItemToDTOConverter orderItemToDTOConverter;
	@Autowired private AccountToDTOConverter accountToDTOConverter;
	@Autowired private AccountFromDTOConverter accountFromDTOConverter;
	@Autowired private TransactionToDTOConverter transactionToDTOConverter;

	@GetMapping
	public ModelAndView index(final HttpServletRequest req, final ExtendedToken token) {
		final IUser user = userService.get(token.getId());

		final Map<String, Object> hashMap = new HashMap<>();

		final List<ProductDTO> orderDTOList = productService
			.getProductsByUserId(user.getId())
			.stream()
			.map(productToDTOConverter)
			.collect(Collectors.toList());
		hashMap.put("userProducts", (orderDTOList));

		final List<OrderItemDTO> saleDTOList = orderItemService
			.getPayedOrderItemsByProductOwnerId(user.getId())
			.stream()
			.map(orderItemToDTOConverter)
			.collect(Collectors.toList());
		hashMap.put("userSales", saleDTOList);

		final List<TransactionDTO> transactionDTOList = transactionService
			.getTransactionByUser(user, TransactionType.WITHDRAWAL, null)
			.stream()
			.map(transactionToDTOConverter)
			.collect(Collectors.toList());
		hashMap.put("userTransactions", transactionDTOList);

		hashMap.put("userAccount", accountToDTOConverter.apply(accountService.getAccountByUser(user)));

		return new ModelAndView("user.product.list", hashMap);
	}

	@PostMapping
	public String withdraw(
		@Valid @ModelAttribute("userAccount") final AccountDTO accountDTO,
		final BindingResult result) {

		final Account account = accountFromDTOConverter.apply(accountDTO);
		accountService.withdraw(account);
		return "redirect:/user/shop";

		//		if (result.hasErrors()) {
		//			return "userAccount";
		//		} else {
		//			final Account account = accountFromDTOConverter.apply(accountDTO);
		//			a.save(account);
		//			return "redirect:/users";
		//		}
	}

}