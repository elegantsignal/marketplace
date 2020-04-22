package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.RoleName;

public interface IRole extends IBaseEntity {

	RoleName getName();

	void setName(String roleName);
}
