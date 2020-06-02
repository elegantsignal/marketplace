package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.filestorage.WrongFileTypeException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface IBookService {

	IBook get(Integer id);

	IBook getFullInfo(Integer id);

	List<IBook> getAll();

	long getCount(BookFilter filter);

	@Transactional IBook createEntity();

	@Transactional IBook save(IBook entity) throws IOException, WrongFileTypeException;

	@Transactional IBook save(IBook book,
		Map<String, InputStream> inputStreamMap,
		BigDecimal price,
		Integer productOwnerId
	) throws IOException, WrongFileTypeException;

	@Transactional void delete(Integer id);

	@Transactional void deleteAll();

	@Transactional List<IBook> find(BookFilter filter);

	@Transactional List<IBook> search(String string);

	IBook createBook(Integer userId);

	List<IBook> getBooksByGenres(List<String> asList);

	List<IBook> getBooksByGenres(String... asList);

	List<IBook> getBooksByAuthorId(List<Integer> ids);

	List<IBook> getBooksByAuthorId(Integer... ids);
}
