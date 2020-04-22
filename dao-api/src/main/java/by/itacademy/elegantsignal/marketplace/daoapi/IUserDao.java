package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;


public interface IUserDao extends IDao<IUser, Integer> {

	List<IUser> find(UserFilter filter);

	long getCount(UserFilter filter);

	IUser getFullInfo(Integer id);

	IUser getFullInfo(UserFilter filter);

	IUser findOne(UserFilter filter);

}
