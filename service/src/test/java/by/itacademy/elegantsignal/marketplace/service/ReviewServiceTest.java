package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;


public class ReviewServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IReview entity = saveNewReview();

		final IReview entityFromDb = reviewService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());

//		assertEquals(entity.getOrderItem().getId(), entityFromDb.getOrderItem().getId());
		assertEquals(entity.getGrade(), entityFromDb.getGrade());
		assertEquals(entity.getComment(), entityFromDb.getComment());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertEquals(entity.getUpdated(), entityFromDb.getUpdated());
	}
	
	@Test
	public void testGetFullInfo() {
		final IReview entity = saveNewReview();

		final IReview entityFromDb = reviewService.getFullInfo(entity.getId());

		assertEquals(entity.getOrderItem().getId(), entityFromDb.getOrderItem().getId());
	}

	// @Test //TODO: Enable me
	public void testGetAll() {
		assertTrue(reviewService.getAll().isEmpty());

		for (int i = 0; i < 3; i++) {
			saveNewReview();
		}

		final List<IReview> allEntities = reviewService.getAll();
		assertEquals(3, allEntities.size());
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
