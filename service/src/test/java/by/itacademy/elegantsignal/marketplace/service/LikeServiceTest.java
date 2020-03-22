package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;


public class LikeServiceTest extends AbstractTest {

	@Test
	@Transactional
	public void testCreate() {
		final ILike entity = saveNewLike();

		final ILike entityFromDb = likeService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());

		assertEquals(entity.getUser().getId(), entityFromDb.getUser().getId());
		assertEquals(entity.getProduct().getId(), entityFromDb.getProduct().getId());
	}

	@Test
	public void testGetAll() {
		final int initialCount = likeService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLike();
		}

		final List<ILike> allEntities = likeService.getAll();

		for (final ILike entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getUser());
			assertNotNull(entityFromDb.getProduct());
			assertNotNull(entityFromDb.getCreated());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ILike entity = saveNewLike();
		likeService.delete(entity.getId());
		assertNull(likeService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewLike();
		likeService.deleteAll();
		assertEquals(0, likeService.getAll().size());
	}
}
