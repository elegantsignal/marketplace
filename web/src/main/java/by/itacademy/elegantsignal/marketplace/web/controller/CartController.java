package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.*;
import by.itacademy.elegantsignal.marketplace.service.*;
import by.itacademy.elegantsignal.marketplace.web.converter.BookFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderItemToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderItemDTO;
import by.itacademy.elegantsignal.marketplace.web.security.ExtendedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/cart")
public class CartController extends AbstractController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IGenreService genreService;

	@Autowired
	private OrderToDTOConverter orderToDTOConverter;

	@Autowired
	private OrderItemToDTOConverter orderItemToDTOConverter;

	@Autowired
	private BookFromDTOConverter fromDtoConverter;

	@GetMapping()
	public ModelAndView index(final HttpServletRequest req, ExtendedToken token) {

		final IOrder cart = orderService.getCartByUserId(token.getId());
		final List<IOrderItem> orderItems = orderItemService.getOrderItems(cart);

		OrderDTO orderDTO = orderToDTOConverter.apply(cart);
		final List<OrderItemDTO> orderItemDTOs = orderItems.stream().map(orderItemToDTOConverter)
				.collect(Collectors.toList());

		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("cart", orderDTO);
		hashMap.put("cartItems", orderItemDTOs);

		return new ModelAndView("cart.item", hashMap);
	}

	@GetMapping(value = "/{id}/delete")
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		orderItemService.delete(id);
		return "redirect:/cart";
	}

	@GetMapping(value = "/{id}/add")
	public String add(@PathVariable(name = "id", required = true) final Integer id, ExtendedToken token) {
		IOrder order = orderService.getCartByUserId(token.getId());
		IProduct product = productService.get(id);
		orderItemService.createEntity(order, product);
		return "redirect:/cart";
	}

	@GetMapping(value = "/checkout")
	public String checkout(ExtendedToken token) {
		final IOrder cart = orderService.getCartByUserId(token.getId());
		orderService.setStatus(cart,"PAYED");

		return "redirect:/cart";
	}

}
