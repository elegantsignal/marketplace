package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;


public interface IProduct extends IBaseEntity {

	IUser getUser();

	void setUser(IUser user);

	ProductType getType();

	void setType(ProductType type);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

}
