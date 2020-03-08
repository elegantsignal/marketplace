package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;


public interface ILike extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	IProduct getProduct();

	void setProduct(IProduct product);

	Date getCreated();

	void setCreated(Date created);


}
