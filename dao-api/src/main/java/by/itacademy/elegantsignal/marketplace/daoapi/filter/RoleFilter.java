package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.RoleName;


public class RoleFilter extends AbstractFilter {

	private RoleName name;

	public RoleName getName() {
		return name;
	}

	public RoleFilter setName(final String roleName) {
		name = RoleName.valueOf(roleName.toUpperCase());
		return this;
	}
}
