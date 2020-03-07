package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.service.IBookService;


@Service
public class BookServiceImpl implements IBookService {
	private IBookDao bookDao;
	private GenreServiceImpl genreServiceImpl;

	@Autowired
	public BookServiceImpl(IBookDao bookDao, GenreServiceImpl genreServiceImpl) {
		this.bookDao = bookDao;
		this.genreServiceImpl = genreServiceImpl;
	}

	@Override
	public IBook createEntity() {
		return bookDao.createEntity();
	}

	@Override
	public void save(final IBook entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			bookDao.insert(entity);
		} else {
			bookDao.update(entity);
		}
	}

	@Override
	public IBook get(final Integer id) {
		final IBook entity = bookDao.get(id);
		return entity;
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
		final List<IBook> all = bookDao.selectAll();
		return all;
	}

}
