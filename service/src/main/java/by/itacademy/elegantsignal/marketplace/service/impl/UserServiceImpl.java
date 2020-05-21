package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IUserDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final IUserDao userDao;

	@Autowired
	public UserServiceImpl(final IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public IUser createEntity() {
		return userDao.createEntity();
	}

	@Override
	public void save(final IUser user) {
		final Date modifiedOn = new Date();
		user.setUpdated(modifiedOn);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getId() == null) {
			LOGGER.info("new user created: {}", user);
			user.setCreated(modifiedOn);
			userDao.insert(user);
		} else {
			LOGGER.info("user updated: {}", user);
			userDao.update(user);
		}
	}

	@Override
	public IUser get(final Integer id) {
		return userDao.get(id);
	}

	@Override
	public IUser getUserByEmail(final String email) {
		final UserFilter filter = new UserFilter();
		filter.setEmail(email);

		try {
			return userDao.getFullInfo(filter);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public IUser findOne(final UserFilter filter) {
		return userDao.findOne(filter);
	}

	@Override
	public IUser getFullInfo(final Integer id) {
		return userDao.getFullInfo(id);
	}

	@Override
	public void delete(final Integer id) {
		userDao.delete(id);
	}

	@Override
	public void deleteAll() {
		userDao.deleteAll();
	}

	@Override
	public List<IUser> getAll() {
		return userDao.selectAll();
	}

	@Override
	public List<IUser> find(final UserFilter filter) {
		return userDao.find(filter);
	}

	@Override
	public long getCount(final UserFilter filter) {
		return userDao.getCount(filter);
	}

	@Override
	public boolean isPasswordMatch(final IUser user, final String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}
}
