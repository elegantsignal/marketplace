package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;


@Entity(name = "User")
@javax.persistence.Table(name = "`user`")
public class User extends BaseEntity implements IUser {

	@Column private String name;
	@Column private String email;
	@Column private String password;
	@Column(updatable = false) private Date created;
	@Column private Date updated;

	@JoinTable(name = "user_2_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
	private Set<IRole> role;

	@Override public String getName() {
		return name;
	}

	@Override public IUser setName(final String name) {
		this.name = name;
		return this;
	}

	@Override public String getEmail() {
		return email;
	}

	@Override public IUser setEmail(final String email) {
		this.email = email;
		return this;
	}

	@Override public String getPassword() {
		return password;
	}

	@Override public IUser setPassword(final String password) {
		this.password = password;
		return this;
	}

	@Override public Date getCreated() {
		return created;
	}

	@Override public IUser setCreated(final Date created) {
		this.created = created;
		return this;
	}

	@Override public Date getUpdated() {
		return updated;
	}

	@Override public IUser setUpdated(final Date updated) {
		this.updated = updated;
		return this;
	}

	@Override public Set<IRole> getRole() {
		return role;
	}

	@Override public IUser setRole(final Set<IRole> role) {
		this.role = role;
		return this;
	}

}
