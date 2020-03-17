package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IUserAccountDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserAccountFilter;
import by.itacademy.elegantsignal.marketplace.service.IUserAccountService;


@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	private final IUserAccountDao userAccountDao;

	@Autowired
	public UserAccountServiceImpl(final IUserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	@Override
	public IUserAccount createEntity() {
		return userAccountDao.createEntity();
	}

	@Override
	public void save(final IUserAccount entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			LOGGER.info("new user created: {}", entity);
			entity.setCreated(modifiedOn);
			userAccountDao.insert(entity);
		} else {
			LOGGER.info("user updated: {}", entity);
			userAccountDao.update(entity);
		}
	}

	@Override
	@Deprecated
	public void saveWithId(final IUserAccount entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		entity.setCreated(modifiedOn);
		userAccountDao.insert(entity);
	}

	@Override
	public IUserAccount get(final Integer id) {
		final IUserAccount entity = userAccountDao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		userAccountDao.delete(id);
	}

	@Override
	public void deleteAll() {
		userAccountDao.deleteAll();
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = userAccountDao.selectAll();
		return all;
	}

	@Override
	public List<IUserAccount> find(final UserAccountFilter filter) {
		return userAccountDao.find(filter);
	}
}
