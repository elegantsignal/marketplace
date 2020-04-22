package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IRoleDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.RoleFilter;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.IRoleService;


@Service
public class RoleServiceImpl implements IRoleService {

	private final IRoleDao roleDao;

	@Autowired
	public RoleServiceImpl(final IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public IRole createEntity() {
		return roleDao.createEntity();
	}

	@Override
	public void save(final IRole entity) {
		if (entity.getId() == null) {
			roleDao.insert(entity);
		} else {
			roleDao.update(entity);
		}
	}

	@Override
	public IRole get(final Integer id) {
		return roleDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		roleDao.delete(id);
	}

	@Override
	public void deleteAll() {
		roleDao.deleteAll();
	}

	@Override
	public List<IRole> getAll() {
		return roleDao.selectAll();
	}

	@Override
	@Deprecated
	public void saveWithId(final IRole role) {
		roleDao.insert(role);
	}

	@Override
	public List<IRole> find(final RoleFilter filter) {
		return roleDao.find(filter);
	}

	@Override
	public long getCount(final RoleFilter filter) {
		return roleDao.getCount(filter);
	}

	@Override
	public IRole getRoleByName(String name) {
		return roleDao.getRoleByName(name);
	}
	

}
