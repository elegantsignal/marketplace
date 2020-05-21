package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;

import java.util.Date;


public interface ITransaction extends IBaseEntity {

	IUser getUser();

	ITransaction setUser(IUser saveNewUser);

	TransactionType getType();

	ITransaction setType(TransactionType withdrawal);

	TransactionStatus getStatus();

	ITransaction setStatus(TransactionStatus success);

	Date getCreated();

	ITransaction setCreated(Date created);

	Date getUpdated();

	ITransaction setUpdated(Date update);

}
