package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TransactionServiceTest extends AbstractTest {

	@Test public void testSaveAndGet() {
		final ITransaction transaction = transactionService.createEntity()
			.setUser(saveNewUser(userService.createEntity()))
			.setAmount(BigDecimal.valueOf(10))
			.setType(TransactionType.PAYMENT)
			.setStatus(TransactionStatus.SUCCESS);

		transactionService.save(transaction);

		final ITransaction transactionFromDB = transactionService.getById(transaction.getId());

		assertNotNull(transactionFromDB);
		assertNotNull(transaction.getAmount());
		assertNotNull(transaction.getType());
		assertNotNull(transaction.getStatus());
	}

	@Test public void testGetTransactionByUserId() {
		final IUser user = saveNewUser(userService.createEntity());
		for (int i = 0; i < 3; i++) {
			final ITransaction transaction = transactionService
				.createEntity()
				.setUser(user)
				.setAmount(BigDecimal.valueOf(10))
				.setType(TransactionType.WITHDRAWAL)
				.setStatus(TransactionStatus.SUCCESS);
			transactionService.save(transaction);
		}

		assertEquals(3, transactionService.getTransactionByUserId(
			user.getId(),
			TransactionType.WITHDRAWAL,
			TransactionStatus.SUCCESS).size()
		);

		assertEquals(3, transactionService.getTransactionByUserId(
			user.getId(),
			TransactionType.WITHDRAWAL,
			null).size()
		);

		assertTrue(transactionService.getTransactionByUserId(
			user.getId(),
			TransactionType.PAYMENT,
			TransactionStatus.SUCCESS).isEmpty());

		assertTrue(transactionService.getTransactionByUserId(
			user.getId(),
			TransactionType.WITHDRAWAL,
			TransactionStatus.FAILED).isEmpty());
	}

}
