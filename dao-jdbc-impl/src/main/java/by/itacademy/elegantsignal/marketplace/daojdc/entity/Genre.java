package by.itacademy.elegantsignal.marketplace.daojdc.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;

public class Genre extends BaseEntity implements IGenre {

	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

}
