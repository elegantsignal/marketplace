package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Genre extends BaseEntity implements IGenre {

	@Column
	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IGenre setName(final String name) {
		this.name = name;
		return this;
	}
}
