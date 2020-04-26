package by.itacademy.elegantsignal.marketplace.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileStorage;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileUtils;
import by.itacademy.elegantsignal.marketplace.service.IBookService;


@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDao bookDao;

	@Autowired
	private IFileStorage fileStorage;

	@Autowired
	private IFileUtils fileUtils;

	@Override
	public IBook createEntity() {
		return bookDao.createEntity();
	}

	@Override
	public void save(final IBook book) throws IOException {
		final Date modifiedOn = new Date();

		book.setUpdated(modifiedOn);

		if (book.getId() == null) {
			book.setId(book.getProduct().getId());
			book.setCreated(modifiedOn);
			bookDao.insert(book);
		} else {
			bookDao.update(book);
		}
	}

	@Override
	public void save(final IBook book, final InputStream inputStream) throws IOException {

		book.setCover(fileUtils.saveTmpImage(inputStream));
		fileStorage.saveCover(book);

		save(book);
	}

	@Override
	public IBook get(final Integer id) {
		return bookDao.get(id);
	}

	@Override
	public IBook getFullInfo(final Integer id) {
		return bookDao.getFullInfo(id);

	}

	@Override
	public void delete(final Integer id) {
		bookDao.delete(id);
	}

	@Override
	public void deleteAll() {
		bookDao.deleteAll();
	}

	@Override
	public List<IBook> getAll() {
		return bookDao.selectAll();
	}

	@Override
	public List<IBook> find(final BookFilter filter) {
		return bookDao.find(filter);
	}

	@Override
	@Deprecated
	public void saveWithId(final IBook book) {
		final Date modifiedOn = new Date();
		book.setUpdated(modifiedOn);
		book.setCreated(modifiedOn);
		bookDao.insert(book);
	}

	@Override
	public long getCount(final BookFilter filter) {
		return bookDao.getCount(filter);
	}

}
