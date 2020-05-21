package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;
import java.util.Set;


public interface IUser extends IBaseEntity {

	String getName();

	IUser setName(String name);

	String getEmail();

	IUser setEmail(String email);

	String getPassword();

	IUser setPassword(String password);

	Date getCreated();

	IUser setCreated(Date created);

	Date getUpdated();

	IUser setUpdated(Date update);

	Set<IRole> getRole();

	IUser setRole(Set<IRole> roleList);

}
