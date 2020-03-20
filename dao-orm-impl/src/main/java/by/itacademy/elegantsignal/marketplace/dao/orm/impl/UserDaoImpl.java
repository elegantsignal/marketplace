package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.User;
import by.itacademy.elegantsignal.marketplace.daoapi.IUserDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;


@Repository
public class UserDaoImpl extends AbstractDaoImpl<IUser, Integer> implements IUserDao {

	protected UserDaoImpl() {
		super(User.class);
	}

	@Override
	public IUser createEntity() {
		return new User();
	}

	@Override
	public List<IUser> find(final UserFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(final UserFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}
