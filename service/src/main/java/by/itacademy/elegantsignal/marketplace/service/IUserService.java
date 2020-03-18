package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;


public interface IUserService {

	IUser get(Integer id);

	List<IUser> getAll();

	void save(IUser entity);

	void delete(Integer id);

	void deleteAll();

	IUser createEntity();

	@Deprecated
	void saveWithId(IUser entity);

	List<IUser> find(UserFilter filter);

	long getCount(UserFilter filter);

}
