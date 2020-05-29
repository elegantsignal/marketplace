package by.itacademy.elegantsignal.marketplace.daoapi;

import java.awt.print.Book;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;


public interface IBookDao extends IDao<IBook, Integer> {

	List<IBook> find(BookFilter filter);

	long getCount(BookFilter filter);

	IBook getFullInfo(Integer id);

	List<IBook> search(String text);

	List<IBook> getBooksByGenres(List<String> genres);
}
