package by.itacademy.elegantsignal.marketplace.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;


public interface IBookService {

	IBook get(Integer id);

	List<IBook> getAll();

	@Transactional
	void save(IBook entity) throws IOException;

	@Transactional
	void save(IBook book, InputStream inputStream) throws IOException;

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

	long getCount(BookFilter filter);

	IBook getFullInfo(Integer id);

	@Transactional
	List<IBook> search(String string);
}
