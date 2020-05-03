package by.itacademy.elegantsignal.marketplace.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.RoleName;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@Autowired
	protected IUserService userService;

	@Autowired
	protected IProductService productService;

	@Autowired
	protected IBookService bookService;

	@Autowired
	protected IGenreService genreService;

	@Autowired
	protected ILikeService likeService;

	@Autowired
	protected IOrderService orderService;

	@Autowired
	protected IOrderItemService orderItemService;

	@Autowired
	protected IReviewService reviewService;

	@Autowired
	protected IRoleService roleService;

	private static final Random RANDOM = new Random();

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.user}")
	private String user;

	@Value("${jdbc.password}")
	private String password;

	@BeforeEach
	public final void recreateTestDB() throws SQLException, IOException {
		final long stampBefore = System.currentTimeMillis();

		final Connection conn = DriverManager.getConnection(url, user, password);

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

		LOGGER.info("Database recreated in {} seconds.",
				Double.valueOf((System.currentTimeMillis() - stampBefore) / 1000));
	}

	private String getScript() {

		final StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(
				Paths.get("../docs/database/marketplace.sql"),
				StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	protected int getRandomFromRange(final Integer max) {
		return RANDOM.nextInt(max);
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	public static <T extends Enum<?>> T randomEnum(final Class<T> clazz) {
		final int x = RANDOM.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	protected IUser saveNewUser() {
		final IUser user = userService.createEntity();
		user.setName("User-" + getRandomPrefix());
		user.setEmail("email-" + getRandomPrefix());
		user.setPassword("password-" + getRandomPrefix());
		userService.save(user);
		return user;
	}

	protected IUser saveNewUser(final Set<IRole> roleSet) {
		final IUser user = userService.createEntity();
		user.setName("User-" + getRandomPrefix());
		user.setEmail("email-" + getRandomPrefix());
		user.setPassword("password-" + getRandomPrefix());
		user.setRole(roleSet);
		userService.save(user);
		return user;
	}

	protected IBook saveNewBook() throws IOException {
		return saveNewBook(saveNewProduct());
	}

	protected IBook saveNewBook(final IProduct product) throws IOException {
		final IBook book = bookService.createEntity();
		book.setProduct(product);

		book.setTitle("The title#" + getRandomPrefix());
		book.setCover(new File("/tmp/" + getRandomPrefix()));
		book.setPublished(LocalDate.now());
		book.setDescription("Description-" + getRandomPrefix());
		bookService.save(book);
		return book;
	}

	protected IGenre saveNewGenre()  {
		final IGenre entity = genreService.createEntity();
		entity.setName("Genre-" + getRandomPrefix());
		genreService.save(entity);
		return entity;
	}

	protected ILike saveNewLike() {
		final ILike entity = likeService.createEntity();
		entity.setUser(saveNewUser());
		entity.setProduct(saveNewProduct());
		entity.setCreated(new Date());
		likeService.save(entity);
		return entity;
	}

	protected IOrder saveNewOrder() {
		final IOrder order = orderService.createEntity();
		order.setUser(saveNewUser());
		order.setStatus(OrderStatus.CART);
		orderService.save(order);
		return order;
	}

	protected IProduct saveNewProduct() {
		final IUser user = saveNewUser();
		return saveNewProduct(user);
	}

	protected IProduct saveNewProduct(final IUser user) {
		final IProduct product = productService.createEntity();
		product.setUser(user);
		product.setType(randomEnum(ProductType.class));
		product.setPrice((BigDecimal.valueOf(getRandomObjectsCount())));
		productService.save(product);
		return product;
	}

	protected IOrderItem saveNewOrderItem() {
		final IOrderItem entity = orderItemService.createEntity();
		entity.setOrder(saveNewOrder());
		entity.setProduct(saveNewProduct());
		entity.setAmount(BigDecimal.valueOf(getRandomFromRange(100)).movePointLeft(2));
		orderItemService.save(entity);
		return entity;
	}

	IOrderItem saveNewOrderItem(IOrder order) {
		final IOrderItem orderItem = orderItemService.createEntity();
		orderItem.setOrder(order);
		orderItem.setProduct(saveNewProduct());
		orderItem.setAmount(BigDecimal.valueOf(getRandomFromRange(100)).movePointLeft(2));
		orderItemService.save(orderItem);
		return orderItem;
	}

	protected IReview saveNewReview() {
		final IReview entity = reviewService.createEntity();
		entity.setOrderItem(saveNewOrderItem());
		entity.setGrade(getRandomFromRange(5));
		entity.setComment("Comment-" + getRandomPrefix());
		reviewService.save(entity);
		return entity;
	}

	protected IRole saveNewRole() {
		final IRole role = roleService.createEntity();
		role.setName(randomEnum(RoleName.class).toString());
		roleService.save(role);
		return role;
	}
}
