package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter @Setter @Accessors(chain = true)
public class BookFilter extends AbstractFilter {

	private IUser user;
	private IGenre genre;

}
