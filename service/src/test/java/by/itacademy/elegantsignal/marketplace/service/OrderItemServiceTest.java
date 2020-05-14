package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class OrderItemServiceTest extends AbstractTest {

	@Test
	@Transactional
	public void testCreate() {
		final IOrderItem entity = saveNewOrderItem(orderItemService.createEntity());
		final IOrderItem entityFromDb = orderItemService.get(entity.getId());

		assertEquals(entity.getOrder().getId(), entityFromDb.getOrder().getId());
		assertEquals(entity.getProduct().getId(), entityFromDb.getProduct().getId());
		assertEquals(entity.getAmount(), entityFromDb.getAmount());
	}

	@Test
	public void testGetAll() {
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

	@Test
	public void testDelete() {
		final IOrderItem entity = saveNewOrderItem(orderItemService.createEntity());
		orderItemService.delete(entity.getId());
		assertNull(orderItemService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewOrderItem(orderItemService.createEntity());
		orderItemService.deleteAll();
		assertEquals(0, orderItemService.getAll().size());
	}

}
