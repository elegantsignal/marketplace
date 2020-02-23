package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;

public interface IUserAccount extends IBaseEntity {

	String getName();

	void setName(String name);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String passwod);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

}
