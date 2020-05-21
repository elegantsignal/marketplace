package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class OrderItemServiceTest extends AbstractTest {

	@Test @Transactional public void testCreate() {
		final IOrderItem entity = saveNewOrderItem(orderItemService.createEntity());
		final IOrderItem entityFromDb = orderItemService.get(entity.getId());

		assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
		assertEquals(entity.getProduct().getId(), entityFromDb.getProduct().getId());
		assertEquals(entity.getAmount(), entityFromDb.getAmount());
	}

	@Test public void testGetAll() {
		final int initialCount = orderItemService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrderItem(orderItemService.createEntity());
		}

		final List<IOrderItem> allEntities = orderItemService.getAll();

		for (final IOrderItem entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getOrder());
			assertNotNull(entityFromDb.getProduct());
			assertNotNull(entityFromDb.getAmount());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test public void testDelete() {
		final IOrderItem entity = saveNewOrderItem(orderItemService.createEntity());
		orderItemService.delete(entity.getId());
		assertNull(orderItemService.get(entity.getId()));
	}

	@Test public void testDeleteAll() {
		saveNewOrderItem(orderItemService.createEntity());
		orderItemService.deleteAll();
		assertEquals(0, orderItemService.getAll().size());
	}

	@Test public void testGetPayedOrderItemsByProductOwnerId() {
		final IUser owner = saveNewUser(userService.createEntity());
		final IUser customer = saveNewUser(userService.createEntity());

		for (int i = 0; i < 10; i++) {
			final IProduct ownerProduct = saveNewProduct(productService.createEntity().setUser(owner).setPrice(BigDecimal.valueOf(10)));
			cartService.addToCart(customer.getId(), ownerProduct);

			// save products with other owners
			final IProduct anonymousProduct = saveNewProduct(productService.createEntity().setPrice(BigDecimal.valueOf(3)));
			cartService.addToCart(customer.getId(), anonymousProduct);

		}

		paymentService.checkout(cartService.getCartByUserId(customer.getId()));

		// ensure that we checkout all orderItems
		final List<IOrderItem> customerOrders = orderItemService.getOderItemsByUserId(customer.getId());
		assertEquals(20, customerOrders.size());

		final BigDecimal totalSalesAmount = accountService.getTotalSalesAmount(owner);
		assertEquals(0, BigDecimal.valueOf(100).compareTo(totalSalesAmount));
	}

}

