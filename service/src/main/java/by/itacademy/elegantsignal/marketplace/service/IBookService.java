package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public interface IBookService {

	IBook get(Integer id);

	IBook getFullInfo(Integer id);

	List<IBook> getAll();

	long getCount(BookFilter filter);

	@Transactional IBook createEntity();

	@Transactional IBook save(IBook entity);

	@Transactional void save(IBook book, Map<String, InputStream> inputStreamMap);

	@Transactional void delete(Integer id);

	@Transactional void deleteAll();

	@Transactional List<IBook> find(BookFilter filter);

	@Transactional List<IBook> search(String string);

	IBook createBook(Integer userId);
}
