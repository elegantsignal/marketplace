package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;


@Entity
public class Genre extends BaseEntity implements IGenre {

	@Column
	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}
}
