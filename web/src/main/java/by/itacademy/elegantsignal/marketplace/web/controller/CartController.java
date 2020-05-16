package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.service.ICartService;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import by.itacademy.elegantsignal.marketplace.service.IPaymentService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/cart")
public class CartController extends AbstractController {

	@Autowired private ICartService cartService;
	@Autowired private IOrderService orderService;
	@Autowired private IOrderItemService orderItemService;
	@Autowired private IProductService productService;
	@Autowired private OrderToDTOConverter orderToDTOConverter;
	@Autowired private OrderItemToDTOConverter orderItemToDTOConverter;
	@Autowired private IPaymentService paymentService;

	@GetMapping()
	public ModelAndView index(final HttpServletRequest req, final ExtendedToken token) {
		final Map<String, Object> hashMap = new HashMap<>();
		final Integer userId = token.getId();

		// Cart
		final IOrder cart = cartService.getCartByUserId(userId);
		final OrderDTO cartDTO = orderToDTOConverter.apply(cart);
		final List<OrderItemDTO> cartItemDTOs = cart
			.getOrderItems()
			.stream()
			.map(orderItemToDTOConverter)
			.collect(Collectors.toList());

		hashMap.put("cart", cartDTO);
		hashMap.put("cartItems", cartItemDTOs);

		// Orders
		final List<IOrder> orderList = orderService.getOrdersByUserId(userId);
		final List<OrderDTO> orderDTOList = orderList
			.stream()
			.map(orderToDTOConverter)
			.collect(Collectors.toList());

		hashMap.put("user_orders", orderDTOList);

		return new ModelAndView("cart.item", hashMap);
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id", required = true) final Integer id, final ExtendedToken token) {
		cartService.removeFromCart(token.getId(), orderItemService.get(id));
		return "redirect:/cart";
	}

	@GetMapping("/{id}/add")
	public String add(@PathVariable(name = "id", required = true) final Integer id, final ExtendedToken token) {
		final IProduct product = productService.get(id);
		cartService.addToCart(token.getId(), product);
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String checkout(final ExtendedToken token) {
		final IOrder userCart = cartService.getCartByUserId(token.getId());
		paymentService.checkout(userCart);
		return "redirect:/cart";
	}

}
