package by.itacademy.elegantsignal.marketplace.seed;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.*;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yaml.snakeyaml.Yaml;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;


public class Seed {

	private static final Path SEED_DIR = Paths.get("docs/seed/");
	private static final Path SEED_YML = SEED_DIR.resolve("seed.yml");

	ApplicationContext context = new ClassPathXmlApplicationContext("service-context-test.xml");

	IUserService userService = context.getBean(IUserService.class);
	IRoleService roleService = context.getBean(IRoleService.class);
	IProductService productService = context.getBean(IProductService.class);
	IOrderService orderService = context.getBean(IOrderService.class);
	IOrderItemService orderItemService = context.getBean(IOrderItemService.class);
	IBookService bookService = context.getBean(IBookService.class);
	IGenreService genreService = context.getBean(IGenreService.class);

	public <T> void seedData() throws IOException, SQLException {
		recreateTestDB();
		final String content = new String(Files.readAllBytes(SEED_YML));
		final Yaml yaml = new Yaml();
		final Map<String, List<T>> document = yaml.load(content);
		populate(document);
	}

	public final void recreateTestDB() throws SQLException {

		final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgres", "1");

		try {
			final Statement stmt = conn.createStatement();
			try {
				stmt.execute("DROP SCHEMA IF EXISTS \"public\" CASCADE;");
				stmt.execute("CREATE SCHEMA \"public\";");
				stmt.execute(getScript());
			} finally {
				stmt.close();
			}
		} finally {
			conn.close();
		}
	}

	private String getScript() {
		final StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(
			Paths.get("docs/database/marketplace.sql"),
			StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
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

		final Set<IRole> roleSet = new HashSet<>();
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
