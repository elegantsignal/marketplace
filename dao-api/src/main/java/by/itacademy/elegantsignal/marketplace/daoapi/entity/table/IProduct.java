package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;

import java.math.BigDecimal;
import java.util.Date;


public interface IProduct extends IBaseEntity {

	IUser getUser();

	IProduct setUser(IUser user);

	ProductType getType();

	IProduct setType(ProductType type);

	BigDecimal getPrice();

	IProduct setPrice(BigDecimal price);

	Date getCreated();

	IProduct setCreated(Date created);

	Date getUpdated();

	IProduct setUpdated(Date update);

	IBook getBook();

	IProduct setBook(IBook book);

}
