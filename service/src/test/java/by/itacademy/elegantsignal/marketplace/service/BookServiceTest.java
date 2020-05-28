package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookServiceTest extends AbstractTest {

	@Test public void testCreate() {
		final IBook book = saveNewBook(bookService.createEntity());
		final IBook bookFromDb = bookService.getFullInfo(book.getId());

		assertEquals(book.getId(), bookFromDb.getId());
		assertEquals(book.getId(), bookFromDb.getProduct().getId());

		assertEquals(book.getCreated(), book.getUpdated());
		assertEquals(book.getCreated(), bookFromDb.getCreated());
		assertEquals(book.getUpdated(), bookFromDb.getUpdated());

		assertEquals(book.getTitle(), bookFromDb.getTitle());
		assertEquals(book.getAuthor(), bookFromDb.getAuthor());
		assertEquals(book.getDescription(), bookFromDb.getDescription());
		assertEquals(book.getCover(), bookFromDb.getCover());
		assertEquals(book.getPublished(), bookFromDb.getPublished());
		assertEquals(book.getPdf(), bookFromDb.getPdf());
	}

	@Test public void testGetAll() {
		final int initialCount = bookService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBook(bookService.createEntity());
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

	@Test public void testDelete() {
		final IBook book = saveNewBook(bookService.createEntity());

		bookService.delete(book.getId());
		assertNull(bookService.get(book.getId()));

		// Corresponding product should be also deleted
		assertNull(productService.get(book.getProduct().getId()));
	}

	@Test public void testDeleteAll() {
		saveNewBook(bookService.createEntity());
		bookService.deleteAll();
		assertEquals(0, bookService.getAll().size());
	}

	@Test public void testBook2Genre() throws IOException {
		final IBook book = saveNewBook(bookService.createEntity());

		final Set<IGenre> genreSet = new HashSet<IGenre>();
		final int genreCount = getRandomObjectsCount();
		for (int i = 0; i < genreCount; i++) {
			genreSet.add(saveNewGenre());
		}

		book.setGenre(genreSet);
		bookService.save(book);

		final IBook bookFromDb = bookService.getFullInfo(book.getId());

		assertEquals(genreCount, bookFromDb.getGenre().size());
	}

	@Test public void testUserBooks() {
		final IUser user = saveNewUser(userService.createEntity());

		final int bookCount = getRandomObjectsCount();
		for (int i = 0; i < bookCount; i++) {
			final IProduct product = saveNewProduct(productService.createEntity().setUser(user));
			saveNewBook(bookService.createEntity().setProduct(product));
		}

		// Generate several addition books
		for (int i = 0; i < bookCount; i++) {
			saveNewBook(bookService.createEntity());
		}

		final BookFilter bookFilter = new BookFilter(user);
		final List<IBook> booksList = bookService.find(bookFilter);

		assertEquals(bookCount, booksList.size());
	}

	@Test public void testSearch() {
		final List<IBook> result = bookService.search("test");
		assertTrue(result.isEmpty());

		final List<String> titleList = Stream
			.of("foo", "bar", "foo bar")
			.collect(Collectors.toList());

		titleList.forEach(title -> {
			final IBook book = bookService.createEntity();
			book.setTitle(title);
			saveNewBook(book);
		});

		final List<IBook> foundBooks = bookService.search("foo");
		assertEquals(2, foundBooks.size());
	}

}
