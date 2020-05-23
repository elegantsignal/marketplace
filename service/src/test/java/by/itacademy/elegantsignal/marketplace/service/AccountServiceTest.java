package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AccountServiceTest extends AbstractTest {

	@Test public void testGetTotalSalesAmount() {
		final IUser user = saveNewUser(userService.createEntity());
		for (int i = 0; i < 6; i++) {
			final IProduct product;
			if (i % 2 == 0) {
				product = saveNewProduct(productService.createEntity().setUser(user).setPrice(BigDecimal.valueOf(5)));
			} else {
				product = saveNewProduct(productService.createEntity());
			}
			saveNewBook(bookService.createEntity().setProduct(product));
			cartService.addToCart(user.getId(), product);
		}
		paymentService.checkout(cartService.getCartByUserId(user.getId()));

		final BigDecimal totalSalesAmount = accountService.getTotalSalesAmount(user);
		assertEquals(0, BigDecimal.valueOf(15).compareTo(totalSalesAmount));
	}

	@Test public void testWithdraw() {
		// Prepare data
		final IUser user = saveNewUser(userService.createEntity());
		for (int i = 0; i < 4; i++) {
			cartService.addToCart(user.getId(), saveNewProduct(productService.createEntity().setUser(user).setPrice(BigDecimal.valueOf(100))));
		}
		final BigDecimal balanceByUser = accountService.getSpendableBalanceByUser(user);
		assertEquals(BigDecimal.valueOf(0), balanceByUser);
		assertThrows(RuntimeException.class, () -> accountService.withdraw(user, BigDecimal.valueOf(10)));

		paymentService.checkout(cartService.getCartByUserId(user.getId()));

		assertEquals(BigDecimal.valueOf(400.0), accountService.getSpendableBalanceByUser(user));

		// Test
		accountService.withdraw(user, BigDecimal.valueOf(100));
		assertEquals(BigDecimal.valueOf(300.0), accountService.getSpendableBalanceByUser(user));
	}

	@Test public void testGetBalanceByUser() {
		final IUser user = saveNewUser(userService.createEntity());
		for (int i = 0; i < 6; i++) {
			cartService.addToCart(user.getId(), saveNewProduct(productService.createEntity().setUser(user).setPrice(BigDecimal.valueOf(10))));
		}

		paymentService.checkout(cartService.getCartByUserId(user.getId()));
		saveNewTransaction(transactionService
			.createEntity()
			.setUser(user)
			.setAmount(BigDecimal.valueOf(40))
			.setType(TransactionType.WITHDRAWAL)
			.setStatus(TransactionStatus.SUCCESS)
		);

		assertEquals(BigDecimal.valueOf(20).stripTrailingZeros(), accountService.getSpendableBalanceByUser(user).stripTrailingZeros());
	}
}
