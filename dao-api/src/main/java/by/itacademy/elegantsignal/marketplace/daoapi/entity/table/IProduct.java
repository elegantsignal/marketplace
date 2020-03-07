package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;


public interface IProduct extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	ProductType getType();

	void setType(ProductType type);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

}
