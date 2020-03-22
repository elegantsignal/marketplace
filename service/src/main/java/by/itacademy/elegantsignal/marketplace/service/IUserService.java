package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;


public interface IUserService {

	IUser get(Integer id);

	List<IUser> getAll();

	@Transactional
	void save(IUser entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	IUser createEntity();

	@Deprecated
	@Transactional
	void saveWithId(IUser entity);

	List<IUser> find(UserFilter filter);

	long getCount(UserFilter filter);

}
