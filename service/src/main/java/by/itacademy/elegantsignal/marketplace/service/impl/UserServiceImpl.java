package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IUserDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

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
	public void save(final IUser entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			LOGGER.info("new user created: {}", entity);
			entity.setCreated(modifiedOn);
			userDao.insert(entity);
		} else {
			LOGGER.info("user updated: {}", entity);
			userDao.update(entity);
		}
	}

	@Override
	@Deprecated
	public void saveWithId(final IUser entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		entity.setCreated(modifiedOn);
		userDao.insert(entity);
	}

	@Override
	public IUser get(final Integer id) {
		return userDao.get(id);
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
}
