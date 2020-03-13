package by.itacademy.elegantsignal.marketplace.service.repopulator;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.service.AbstractTest;


class SeedTest extends AbstractTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {}

	@Test
	public <T> void SeedDataTest() throws IOException {
		final String content = new String(Files.readAllBytes(Paths.get("../docs/seeddata.yml")));
		final Yaml yaml = new Yaml();
		final Map<String, List<T>> document = yaml.load(content);
		populate(document);
	}

	private <T> void populate(final Map<String, List<T>> document) {
		document.forEach((modelName, modelData) -> {
			switch (modelName) {
			case "UserAccount":
				for (final T userData : modelData) {
					createUser((Map<String, T>) userData);
				}
				break;
			case "Product":
				for (final T productData : modelData) {
					createProduct((Map<String, T>) productData);
				}
				break;
			case "Genre":
				for (final T genreData : modelData) {
					createGenre((Map<String, T>) genreData);
				}
				break;
			case "Book":
				for (final T bookData : modelData) {
					createBook((Map<String, T>) bookData);
				}
				break;
			default:
				break;
			}
		});
	}

	private <T> void createGenre(final Map<String, T> genreData) {
		final IGenre genre = genreService.createEntity();
		genre.setId((Integer) genreData.get("id"));
		genre.setName((String) genreData.get("name"));
		genreService.saveWithId(genre);
	}

	protected <T> void createUser(final Map<String, T> userData) {
		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId((Integer) userData.get("id"));
		userAccount.setName((String) userData.get("name"));
		userAccount.setEmail((String) userData.get("email"));
		userAccount.setPassword((String) userData.get("password"));
		userAccount.setCreated(new Date());
		userAccount.setUpdated(new Date());
		userAccountService.saveWithId(userAccount);
	}

	private <T> void createProduct(final Map<String, T> productData) {
		final IProduct product = productService.createEntity();
		product.setId((Integer) productData.get("id"));
		product.setUserAccount(userAccountService.get((Integer) productData.get("user_account_id")));
		product.setType(ProductType.valueOf((String) productData.get("type")));
		product.setPrice(BigDecimal.valueOf((Integer) productData.get("price")));
		product.setCreated(new Date());
		product.setUpdated(new Date());
		productService.saveWithId(product);
	}

	private <T> void createBook(final Map<String, T> bookData) {
		final IBook book = bookService.createEntity();
		book.setId((Integer) bookData.get("id"));
		book.setProduct(productService.get((Integer) bookData.get("product_id")));
		book.setTitle((String) bookData.get("title"));
		book.setCover(Paths.get((String) bookData.get("cover")));
		book.setPublished((Date) bookData.get("published"));
		book.setDescription((String) bookData.get("description"));
		book.setCreated(new Date());
		book.setUpdated(new Date());
		bookService.saveWithId(book);
	}
}
