package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;

public interface IBookDao extends IDao<IBook, Integer> {

	List<IBook> find(BookFilter filter);

	long getCount(BookFilter filter);
}
