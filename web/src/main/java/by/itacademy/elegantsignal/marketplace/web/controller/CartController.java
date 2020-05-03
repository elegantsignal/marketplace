package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderItemToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderItemDTO;
import by.itacademy.elegantsignal.marketplace.web.security.ExtendedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/cart")
public class CartController extends AbstractController {

	@Autowired private IOrderService orderService;
	@Autowired private IOrderItemService orderItemService;
	@Autowired private IProductService productService;
	@Autowired private IGenreService genreService;
	@Autowired private OrderToDTOConverter orderToDTOConverter;
	@Autowired private OrderItemToDTOConverter orderItemToDTOConverter;
	@Autowired private BookFromDTOConverter fromDtoConverter;

	@GetMapping()
	public ModelAndView index(final HttpServletRequest req, ExtendedToken token) {
		Integer userId = token.getId();
		final Map<String, Object> hashMap = new HashMap<>();

		// Cart
		final IOrder cart = orderService.getCartByUserId(userId);
		final List<IOrderItem> cartItems = orderItemService.getOrderItems(cart);
		OrderDTO cartDTO = orderToDTOConverter.apply(cart);
		final List<OrderItemDTO> cartItemDTOs = cartItems.stream().map(orderItemToDTOConverter).collect(Collectors.toList());
		hashMap.put("cart", cartDTO);
		hashMap.put("cartItems", cartItemDTOs);

		// Orders
		List<IOrder> orders = orderService.getOrdersByUserId(userId);
		final List<IOrderItem> orderItems = new LinkedList<>();
		// TODO: think about it: `orders.forEach(order -> order.setOrderItem(orderItemService.getOrderItems(order)))`
		orders.forEach(order -> orderItems.addAll(orderItemService.getOrderItems(order)));
		final List<OrderItemDTO> orderItemsDTOs = orderItems.stream().map(orderItemToDTOConverter).collect(Collectors.toList());
		hashMap.put("ordersItems", orderItemsDTOs);

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
		orderService.setStatus(cart, OrderStatus.PAYED);

		return "redirect:/cart";
	}

}
