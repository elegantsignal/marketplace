package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.RoleName;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;


@Entity
public class Role extends BaseEntity implements IRole {

	@Column
	@Enumerated(EnumType.STRING)
	private RoleName name;

	@Override
	public RoleName getName() {
		return this.name;
	}

	@Override
	public void setName(final RoleName name) {
		this.name = name;
	}
}
