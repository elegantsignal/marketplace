package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.IUserAccountDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daojdc.UserAccountDaoImpl;
import by.itacademy.elegantsignal.marketplace.service.IUserAccountService;

public class UserAccountServiceImpl implements IUserAccountService {

	private IUserAccountDao dao = new UserAccountDaoImpl();

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IUserAccount entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IUserAccount get(final Integer id) {
		final IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		return all;
	}

}
