package by.itacademy.elegantsignal.marketplace.service;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected IProductService productService;
	@Autowired
	protected IBookService bookService;
	@Autowired
	protected IGenreService genreService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		genreService.deleteAll();
		bookService.deleteAll();
		productService.deleteAll();
		userAccountService.deleteAll();
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
