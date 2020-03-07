package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;


public class LanguageServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ILanguage entity = saveNewLanguage();

		final ILanguage entityFromDb = languageService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getCode(), entityFromDb.getCode());
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
	}

	@Test
	public void testGetAll() {
		final int initialCount = languageService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewLanguage();
		}

		final List<ILanguage> allEntities = languageService.getAll();

		for (final ILanguage entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ILanguage entity = saveNewLanguage();
		languageService.delete(entity.getId());
		assertNull(languageService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewLanguage();
		languageService.deleteAll();
		assertEquals(0, languageService.getAll().size());
	}
}
