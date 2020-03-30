package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;
import java.util.Set;


public interface IUser extends IBaseEntity {

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

	Set<IRole> getRole();

	void setRole(Set<IRole> roleList);

}
