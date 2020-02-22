package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

public interface IUserService {

	IUser get(Integer id);

	List<IUser> getAll();

	void save(IUser entity);

	void delete(Integer id);

	void deleteAll();

	IUser createEntity();

}
