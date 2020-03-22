package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;


public class ReviewServiceTest extends AbstractTest {

	@Test
	@Transactional
	public void testCreate() {
		final IReview entity = saveNewReview();

		final IReview entityFromDb = reviewService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());

		assertEquals(entity.getOrderItem().getId(), entityFromDb.getOrderItem().getId());
		assertEquals(entity.getGrade(), entityFromDb.getGrade());
		assertEquals(entity.getComment(), entityFromDb.getComment());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertEquals(entity.getUpdated(), entityFromDb.getUpdated());
	}

	@Test
	public void testGetAll() {
		final int initialCount = reviewService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewReview();
		}

		final List<IReview> allEntities = reviewService.getAll();

		for (final IReview entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getOrderItem());
			assertNotNull(entityFromDb.getGrade());
			assertNotNull(entityFromDb.getComment());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IReview entity = saveNewReview();
		reviewService.delete(entity.getId());
		assertNull(reviewService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewReview();
		reviewService.deleteAll();
		assertEquals(0, reviewService.getAll().size());
	}
}
