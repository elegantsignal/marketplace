package by.itacademy.elegantsignal.marketplace.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;


@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected IProductService productService;
	@Autowired
	protected IBookService bookService;
	@Autowired
	protected IGenreService genreService;

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

		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(
				Paths.get("../docs/database/dumps/marketplace.sql"),
				StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
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

	public Random getRANDOM() {
		return RANDOM;
	}

	public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
		int x = RANDOM.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setName("UserAccount-" + getRandomPrefix());
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		userAccountService.save(entity);
		return entity;
	}

	protected IProduct saveNewProduct() {
		final IProduct entity = productService.createEntity();
		entity.setUserAccount(saveNewUserAccount());
		entity.setType(randomEnum(ProductType.class));
		entity.setPrice((BigDecimal.valueOf(getRandomObjectsCount())));
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		productService.save(entity);
		return entity;
	}

	protected IBook saveNewBook() {
		final IBook entity = bookService.createEntity();
		entity.setProduct(saveNewProduct());
		entity.setTitle("The title#" + getRandomPrefix());
		entity.setCover(Paths.get("img", "cover", getRandomPrefix()));
		entity.setPublished(new Date());
		entity.setDescription("Description-" + getRandomPrefix());
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		bookService.save(entity);
		return entity;
	}

	protected IGenre saveNewGenre() {
		final IGenre entity = genreService.createEntity();
		entity.setName("Genre-" + getRandomPrefix());
		genreService.save(entity);
		return entity;
	}
}
