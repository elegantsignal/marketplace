package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;


public interface IOrder extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);
}
