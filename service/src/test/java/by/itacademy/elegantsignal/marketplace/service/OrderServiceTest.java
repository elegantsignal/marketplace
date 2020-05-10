package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OrderServiceTest extends AbstractTest {

	@Test
	@Transactional
	public void testCreate() {
		final IOrder entity = this.saveNewOrder(orderService.createEntity());
		final IOrder entityFromDb = orderService.get(entity.getId());
		assertNotNull(entityFromDb);
		assertEquals(entity.getUser().getId(), entityFromDb.getUser().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entityFromDb.getCreated(), entityFromDb.getUpdated());
	}

	@Test
	public void testGetAll() {
		final int initialCount = orderService.getAll().size();

		final int randomObjectsCount = this.getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			this.saveNewOrder(orderService.createEntity());
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
		final IOrder entity = this.saveNewOrder(orderService.createEntity());
		orderService.delete(entity.getId());
		assertNull(orderService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		this.saveNewOrder(orderService.createEntity());
		orderService.deleteAll();
		assertEquals(0, orderService.getAll().size());
	}

	@Test
	public void testGetOrdersByUserId() {
		final IUser user = saveNewUser();
		for (int i = 0; i < 4; i++) {
			final OrderStatus orderStatus = (i % 2 == 0) ? OrderStatus.PAYED : OrderStatus.CART;
			final IOrder order = orderService.createEntity().setUser(user).setStatus(orderStatus);
			saveNewOrder(order);

			for (int j = 0; j < 5; j++) {
				final IOrderItem orderItem = orderItemService.createEntity().setOrder(order);
				saveNewOrderItem(orderItem);
			}
		}

		final List<IOrder> userOrders = orderService.getOrdersByUserId(user.getId());
		assertEquals(2, userOrders.size());
		userOrders.forEach(order -> {
			assertNotEquals(OrderStatus.CART,order.getStatus());
			assertNotEquals(0, order.getOrderItems().size());
			order.getOrderItems().forEach(orderItem -> {
				assertNotNull(orderItem.getProduct());
			});
		});
	}
}
