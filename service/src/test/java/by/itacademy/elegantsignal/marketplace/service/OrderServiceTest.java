package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.service.repopulator.SeedTest;
import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;


public class OrderServiceTest extends AbstractTest {

	@Test
	@Transactional
	public void testCreate() {
		final IOrder entity = saveNewOrder();

		final IOrder entityFromDb = orderService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getUser().getId(), entityFromDb.getUser().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testGetAll() {
		final int initialCount = orderService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewOrder();
		}

		final List<IOrder> allEntities = orderService.getAll();

		for (final IOrder entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getUser());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IOrder entity = saveNewOrder();
		orderService.delete(entity.getId());
		assertNull(orderService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewOrder();
		orderService.deleteAll();
		assertEquals(0, orderService.getAll().size());
	}

	@Test
	public void testGetCartByUser() {
		IOrder order = saveNewOrder();
		List<IOrderItem> orderItems = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			orderItems.add(saveNewOrderItem(order));
		}

		order.setOrderItem(orderItems);

		for (int i = 0; i < 2; i++) {
			saveNewOrderItem();
		}

		orderService.save(order);

		IOrder orderFromDb = orderService.getCartByUser(order.getUser());
		assertEquals(order.getUser().getEmail(), orderFromDb.getUser().getEmail());
		assertEquals(orderItems.size(), orderFromDb.getOrderItem().size());
		assertEquals(order.getOrderItem().get(0).getAmount(), orderFromDb.getOrderItem().get(0).getAmount());
	}

}
