package by.itacademy.elegantsignal.marketplace.service.repopulator;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.*;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.*;


public class SeedTest extends AbstractTest {

	private static final Path SEED_DIR = Paths.get("../docs/seed/");
	private static final Path SEED_YML = SEED_DIR.resolve("seed.yml");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

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
							e.printStackTrace();
						}
					}
					break;

				case "Order":
					for (final T orderData : modelData) {
						createOrder((Map<String, T>) orderData);
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

	private <T> void createOrder(Map<String, T> orderData) {

		// get user associated to order
		final UserFilter userFilter = new UserFilter();
		userFilter.setEmail(orderData.get("user_email").toString());
		final IUser user = userService.findOne(userFilter);

		// create order
		final IOrder order = orderService.createEntity();
		order.setUser(user);
		order.setStatus(OrderStatus.valueOf((String) orderData.get("status")));
		orderService.save(order);

		// handel order items
		List<Map<String, T>> orderItems = (List<Map<String, T>>) orderData.get("items");
		for (Map<String, T> orderItemData : orderItems) {
			IOrderItem orderItem = orderItemService.createEntity();
			orderItem.setAmount(BigDecimal.valueOf((Integer) orderItemData.get("price")));
			orderItem.setOrder(order);
			orderItem.setProduct(productService.get((Integer) orderItemData.get("product_id")));
			orderItemService.save(orderItem);
		}
	}

}
