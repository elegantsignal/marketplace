package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderItemToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.ProductToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderItemDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.ProductDTO;
import by.itacademy.elegantsignal.marketplace.web.security.ExtendedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user/shop")
public class UserShopPageController extends AbstractController {

	@Autowired IProductService productService;
	@Autowired IOrderItemService orderItemService;
	@Autowired private ProductToDTOConverter productToDTOConverter;
	@Autowired private OrderItemToDTOConverter orderItemToDTOConverter;

	@GetMapping
	public ModelAndView index(final HttpServletRequest req, final ExtendedToken token) {
		final Map<String, Object> hashMap = new HashMap<>();
		final List<IProduct> productList = productService.getProductsByUserId(token.getId());

		final List<ProductDTO> orderDTOList = productList
			.stream()
			.map(productToDTOConverter)
			.collect(Collectors.toList());

		hashMap.put("userProducts", orderDTOList);

		final List<IOrderItem> saleList = orderItemService.getPayedOrderItemsByProductOwnerId(token.getId());
		final List<OrderItemDTO> saleDTOList = saleList
			.stream()
			.map(orderItemToDTOConverter)
			.collect(Collectors.toList());
		hashMap.put("userSales", saleDTOList);

		return new ModelAndView("user.product.list", hashMap);
	}

}