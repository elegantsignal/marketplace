package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


public class BookFilter extends AbstractFilter {

	private IUser user;

	public BookFilter() {}

	public BookFilter(final IUser user) {
		setUser(user);
	}

	public IUser getUser() {
		return user;
	}

	public void setUser(final IUser user) {
		this.user = user;
	}

}
