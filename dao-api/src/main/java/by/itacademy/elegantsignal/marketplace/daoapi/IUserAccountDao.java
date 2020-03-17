package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserAccountFilter;


public interface IUserAccountDao extends IDao<IUserAccount, Integer> {

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

}
