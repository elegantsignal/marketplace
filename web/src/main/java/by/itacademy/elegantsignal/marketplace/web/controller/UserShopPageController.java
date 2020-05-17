package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.web.converter.OrderItemToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.ProductToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderDTO;
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
	@Autowired private ProductToDTOConverter productToDTOConverter;

	@GetMapping
	public ModelAndView index(final HttpServletRequest req, final ExtendedToken token) {
		final Map<String, Object> hashMap = new HashMap<>();
		final List<IProduct> productList = productService.getProductsByUserId(token.getId());

		final List<ProductDTO> orderDTOList = productList
			.stream()
			.map(productToDTOConverter)
			.collect(Collectors.toList());

		hashMap.put("userProducts", orderDTOList);

		return new ModelAndView("user.product.list", hashMap);
	}

}