package by.itacademy.elegantsignal.marketplace.service;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.service.impl.UserServiceImpl;

public abstract class AbstractTest {
	protected IUserService userService = new UserServiceImpl();

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		userService.deleteAll();

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

	protected IUser saveNewUser() {
		final IUser entity = userService.createEntity();
		entity.setName("User-" + getRandomPrefix());
		entity.setEmail("email-" + getRandomPrefix());
		entity.setPassword("password-" + getRandomPrefix());
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		userService.save(entity);
		return entity;
	}
}
