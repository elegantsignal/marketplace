package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SalesServiceTest extends AbstractTest {

	@Test
	public void testGetSalesByUserId() {
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

		final IOrder usersCart = cartService.getCartByUserId(user.getId());
		paymentService.checkout(usersCart);

		final List<IOrderItem> sales = salesService.getSalesByUserId(user.getId());

		assertEquals(3, sales.size());
		sales.forEach(sale -> {
			assertNotNull(sale.getAmount());
			assertNotNull(sale.getProduct().getBook().getTitle());
		});
	}
}
