package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;

import javax.transaction.Transactional;
import java.util.List;


public interface IUserService {

	IUser get(Integer id);

	IUser getUserByEmail(String username);

	List<IUser> getAll();

	@Transactional void save(IUser entity);

	@Transactional void addNewUser(IUser user);

	@Transactional void delete(Integer id);

	@Transactional void deleteAll();

	@Transactional IUser createEntity();

	List<IUser> find(UserFilter filter);

	IUser findOne(UserFilter filter);

	long getCount(UserFilter filter);

	IUser getFullInfo(Integer id);

	boolean isPasswordMatch(final IUser user, final String password);

}
