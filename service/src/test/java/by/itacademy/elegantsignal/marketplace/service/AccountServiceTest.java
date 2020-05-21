package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class AccountServiceTest extends AbstractTest {

	@Test public void testGetAccountBalance() {
		final IUser user = saveNewUser(userService.createEntity());

		for (int i = 0; i < 6; i++) {
			final IProduct product;

			if (i % 2 == 0) {
				product = saveNewProduct(productService.createEntity().setUser(user));
			} else {
				product = saveNewProduct(productService.createEntity());
			}

			saveNewBook(bookService.createEntity().setProduct(product));
			cartService.addToCart(user.getId(), product);
		}

		paymentService.checkout(cartService.getCartByUserId(user.getId()));

		BigDecimal totalSalesAmount = accountService.getTotalSalesAmount(user);
	}
}
