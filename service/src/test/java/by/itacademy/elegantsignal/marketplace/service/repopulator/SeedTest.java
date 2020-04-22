package by.itacademy.elegantsignal.marketplace.service.repopulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.AbstractTest;


class SeedTest extends AbstractTest {

	private static final Path SEED_DIR = Paths.get("../docs/seed/");
	private static final Path SEED_YML = SEED_DIR.resolve("seed.yml");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {}

	@Test
	public <T> void SeedDataTest() throws IOException {
		final String content = new String(Files.readAllBytes(SEED_YML));
		final Yaml yaml = new Yaml();
		final Map<String, List<T>> document = yaml.load(content);
		populate(document);
	}

	private <T> void populate(final Map<String, List<T>> document) {
		document.forEach((modelName, modelData) -> {
			switch (modelName) {
				case "User":
					for (final T userData : modelData) {
						createUser((Map<String, T>) userData);
					}
					break;
				case "Book":
					for (final T bookData : modelData) {
						try {
							createBook((Map<String, T>) bookData);
						} catch (final IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				default:
					break;
			}
		});
	}

	protected <T> void createUser(final Map<String, T> userData) {
		final IUser user = userService.createEntity();
		user.setName((String) userData.get("name"));
		user.setEmail((String) userData.get("email"));
		user.setPassword((String) userData.get("password"));
		final List<String> userRoles = (List<String>) userData.get("roles");

		final Set<IRole> roleSet = new HashSet<IRole>();
		for (final String roleName : userRoles) {
			roleSet.add(getOrCreateRole(roleName));
		}
		user.setRole(roleSet);
		userService.save(user);
	}

	private IRole getOrCreateRole(final String roleName) {
		IRole role;
		try {
			role = roleService.getRoleByName(roleName);

		} catch (final NoResultException e) {
			role = roleService.createEntity();
			role.setName(roleName);
			roleService.save(role);
		}
		return role;

	}

	private <T> void createBook(final Map<String, T> bookData) throws IOException {

		// get user associated to book
		final UserFilter userFilter = new UserFilter();
		userFilter.setEmail(bookData.get("user_email").toString());
		final IUser user = userService.findOne(userFilter);

		// create product
		final IProduct product = productService.createEntity();
		product.setUser(user);
		product.setPrice(BigDecimal.valueOf((Integer) bookData.get("product_price")));
		product.setType(ProductType.BOOK);
		productService.save(product);

		// handle genres
		final Set<IGenre> genreSet = new HashSet<IGenre>();

		for (final String genreName : (List<String>) bookData.get("genre")) {
			final GenreFilter genreFilter = new GenreFilter();
			genreFilter.setName(genreName);

			IGenre genre;

			try {
				genre = genreService.findOne(genreFilter);
			} catch (final NoResultException e) {
				genre = genreService.createEntity();
				genre.setName(genreName);
				genreService.save(genre);
			}
			genreSet.add(genre);
		}

		final IBook book = bookService.createEntity();
		book.setProduct(product);

		book.setGenre(genreSet);

		book.setTitle((String) bookData.get("title"));
		
		final Date date = (Date) bookData.get("published");
		book.setPublished(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		
		book.setDescription((String) bookData.get("description"));

		final File coverFile = SEED_DIR.resolve((String) bookData.get("cover")).toFile();
		bookService.save(book, new FileInputStream(coverFile));
	}

}
