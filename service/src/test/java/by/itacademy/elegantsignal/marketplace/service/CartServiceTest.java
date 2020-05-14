package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CartServiceTest extends AbstractTest {

	@Autowired private IProductService productService;
	@Autowired private ICartService cartService;

	@Test
	public void testGetNewCart() {
		final IUser user = saveNewUser(userService.createEntity());
		final IOrder cart = cartService.getCartByUserId(user.getId());
		assertNotNull(cart);
	}

	@Test
	public void testAddToCart() {
		final IUser user = saveNewUser(userService.createEntity());
		for (int i = 0; i < 3; i++) {
			cartService.addToCart(user.getId(), saveNewProduct(productService.createEntity()));
			saveNewProduct(productService.createEntity());
		}
		final IOrder cartFromDb = cartService.getCartByUserId(user.getId());

		assertEquals(3, cartFromDb.getOrderItems().size());
		assertEquals(6, productService.getAll().size());
	}

	@Test
	public void testRemoveFromCart() {
		final IUser user = saveNewUser(userService.createEntity());

		for (int i = 0; i < 3; i++) {
			cartService.addToCart(user.getId(), saveNewProduct(productService.createEntity()));
		}

		IOrder cartFromDb = cartService.getCartByUserId(user.getId());
		cartService.removeFromCart(user.getId(), cartFromDb.getOrderItems().get(1));
		cartFromDb = cartService.getCartByUserId(user.getId());
		assertEquals(2, cartFromDb.getOrderItems().size());
	}

}
