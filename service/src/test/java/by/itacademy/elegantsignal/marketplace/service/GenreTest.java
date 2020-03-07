package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;


public class GenreTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IGenre entity = saveNewGenre();

		final IGenre entityFromDb = genreService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
	}

	@Test
	public void testGetAll() {
		final int initialCount = genreService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewGenre();
		}

		final List<IGenre> allEntities = genreService.getAll();

		for (final IGenre entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IGenre entity = saveNewGenre();
		genreService.delete(entity.getId());
		assertNull(genreService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewGenre();
		genreService.deleteAll();
		assertEquals(0, genreService.getAll().size());
	}
}
