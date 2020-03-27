package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.RoleFilter;


public interface IRoleService {

	IRole get(Integer id);

	List<IRole> getAll();

	@Transactional
	void save(IRole entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IRole createEntity();

	@Transactional
	@Deprecated
	void saveWithId(IRole genre);

	List<IRole> find(RoleFilter filter);

	long getCount(RoleFilter filter);

}
