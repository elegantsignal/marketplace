package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;


public class BookServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IBook book = saveNewBook();
		final IBook bookFromDb = bookService.getFullInfo(book.getId());

		assertEquals(book.getId(), bookFromDb.getId());
		assertEquals(book.getId(), bookFromDb.getProduct().getId());

		assertEquals(book.getCreated(), book.getUpdated());
		assertEquals(book.getCreated(), bookFromDb.getCreated());
		assertEquals(book.getUpdated(), bookFromDb.getUpdated());

		assertEquals(book.getTitle(), bookFromDb.getTitle());
		assertEquals(book.getDescription(), bookFromDb.getDescription());
		assertEquals(book.getCover(), bookFromDb.getCover());
		assertEquals(book.getPublished(), bookFromDb.getPublished());
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
		final IBook book = saveNewBook();

		bookService.delete(book.getId());
		assertNull(bookService.get(book.getId()));

		// Corresponding product should be also deleted
		assertNull(productService.get(book.getProduct().getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewBook();
		bookService.deleteAll();
		assertEquals(0, bookService.getAll().size());
	}
	
	@Test
	public void testBook2Genre() {
		final IBook book = saveNewBook();
		
		Set<IGenre> genreSet = new HashSet<IGenre>();
		final int genreCount = getRandomObjectsCount();
		for (int i = 0; i < genreCount; i++) {
			genreSet.add(saveNewGenre());
		}
		
		book.setGenre(genreSet);
		bookService.save(book);
		
		final IBook bookFromDb = bookService.getFullInfo(book.getId());

		assertEquals(genreCount, bookFromDb.getGenre().size());
	}

}
