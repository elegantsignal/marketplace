package by.itacademy.elegantsignal.marketplace.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;


public class BookServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IBook entity = saveNewBook();

		final IBook entityFromDb = bookService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertEquals(entity.getProduct().getId(), entityFromDb.getProduct().getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testGetAll() {
		final int initialCount = bookService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBook();
		}

		final List<IBook> allEntities = bookService.getAll();

		for (final IBook entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getProduct());
			assertNotNull(entityFromDb.getTitle());
			assertNotNull(entityFromDb.getDescription());
			assertNotNull(entityFromDb.getPublished());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IBook entity = saveNewBook();
		bookService.delete(entity.getId());
		assertNull(bookService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewBook();
		bookService.deleteAll();
		assertEquals(0, bookService.getAll().size());
	}
}
