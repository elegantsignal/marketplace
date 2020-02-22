package by.itacademy.elegantsignal.marketplace.daojdc.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBaseEntity;

public abstract class BaseEntity implements IBaseEntity {
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

}