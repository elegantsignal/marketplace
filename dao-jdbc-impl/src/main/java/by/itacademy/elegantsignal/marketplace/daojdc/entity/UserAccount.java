package by.itacademy.elegantsignal.marketplace.daojdc.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	private String name;
	private String email;
	private String password;
	private Date created;
	private Date updated;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
