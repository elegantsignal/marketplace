package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;


public class Language extends BaseEntity implements ILanguage {

	private String code;
	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

}
