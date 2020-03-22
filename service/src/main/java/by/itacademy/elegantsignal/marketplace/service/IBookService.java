package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;


public interface IBookService {

	IBook get(Integer id);

	List<IBook> getAll();

	@Transactional
	void save(IBook entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	IBook createEntity();

	@Transactional
	List<IBook> find(BookFilter filter);

	@Deprecated
	@Transactional
	void saveWithId(IBook book);

}
