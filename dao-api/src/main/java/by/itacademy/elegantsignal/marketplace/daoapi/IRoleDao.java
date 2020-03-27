package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.RoleFilter;


public interface IRoleDao extends IDao<IRole, Integer> {

	List<IRole> find(RoleFilter filter);

	long getCount(RoleFilter filter);

}
