package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


@Entity(name = "User")
@javax.persistence.Table(name = "`user`")
public class User extends BaseEntity implements IUser {

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private Date created;

	@Column
	private Date updated;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

}
