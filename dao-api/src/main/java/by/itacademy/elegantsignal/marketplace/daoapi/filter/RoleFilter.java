package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.RoleName;


public class RoleFilter extends AbstractFilter {

	private RoleName name;

	public RoleName getName() {
		return this.name;
	}

	public void setName(final String roleName) {
		this.name = RoleName.valueOf(roleName.toUpperCase());
	}
}
