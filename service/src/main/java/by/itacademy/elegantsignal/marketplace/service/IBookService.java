package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;


public interface IBookService {

	IBook get(Integer id);

	List<IBook> getAll();

	void save(IBook entity);

	void delete(Integer id);

	void deleteAll();

	IBook createEntity();

	List<IBook> find(BookFilter filter);

}
