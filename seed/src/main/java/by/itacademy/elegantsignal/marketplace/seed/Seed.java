package by.itacademy.elegantsignal.marketplace.seed;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.service.IOrderItemService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.service.IRoleService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


@Service
public class Seed {

	private final String dbSchema = "docs/database/marketplace.sql";
	private final Path SEED_DIR = Paths.get("docs/seed/");
	private final Path SEED_YML = SEED_DIR.resolve("seed.yml");

	ApplicationContext context = new ClassPathXmlApplicationContext("seed-context.xml");

	IUserService userService = context.getBean(IUserService.class);
	IRoleService roleService = context.getBean(IRoleService.class);
	IProductService productService = context.getBean(IProductService.class);
	IOrderService orderService = context.getBean(IOrderService.class);
	IOrderItemService orderItemService = context.getBean(IOrderItemService.class);
	IBookService bookService = context.getBean(IBookService.class);
	IGenreService genreService = context.getBean(IGenreService.class);

	public static void main(String[] args) throws IOException, SQLException {
		Seed seed = new Seed();
		seed.seedData();
	}

	public <T> void seedData() throws IOException, SQLException {
		recreateTestDB();
		final String content = new String(Files.readAllBytes(SEED_YML));
		final Yaml yaml = new Yaml();
		final Map<String, List<T>> document = yaml.load(content);
		populate(document);
	}

	public final void recreateTestDB() throws SQLException {

		// TODO: Why we need credetials here and in db.properties file - ???
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/marketplace", "postgres", "1")) {
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("DROP SCHEMA IF EXISTS \"public\" CASCADE;");
				stmt.execute("CREATE SCHEMA \"public\";");
				stmt.execute(getScript());
			}
		}
	}

	private String getScript() {
		final StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(
			Paths.get(dbSchema),
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
		final IUser user = userService.createEntity()
			.setName((String) userData.get("name"))
			.setEmail((String) userData.get("email"))
			.setPassword((String) userData.get("password"));

		final Set<IRole> roleSet = new HashSet<>();
		final List<String> userRoles = (List<String>) userData.get("roles");
		userRoles.forEach(roleName -> roleSet.add(getOrCreateRole(roleName)));
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

		//		// create product
		//		final IProduct product = productService.createEntity();
		//		product.setUser(user);
		//		product.setPrice(BigDecimal.valueOf((Integer) bookData.get("product_price")));
		//		product.setType(ProductType.BOOK);
		//		productService.save(product);

		// handle genres
		final List<IGenre> genreSet = new ArrayList<>();

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
		//		book.setProduct(product);

		book.setGenres(genreSet);

		book.setTitle((String) bookData.get("title"));
		book.setAuthor((String) bookData.get("author"));

		final Date date = (Date) bookData.get("published");
		book.setPublished(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

		book.setDescription((String) bookData.get("description"));

		final File coverFile = SEED_DIR.resolve((String) bookData.get("cover")).toFile();
		final File pdfFile = SEED_DIR.resolve((String) bookData.get("pdf")).toFile();
		final Map<String, InputStream> bookFiles = new HashMap<>();
		bookFiles.put("cover", new FileInputStream(coverFile));
		bookFiles.put("pdf", new FileInputStream(pdfFile));
		bookService.save(book, bookFiles, BigDecimal.valueOf((Integer) bookData.get("product_price")), user.getId());
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
