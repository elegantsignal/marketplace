package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daojdc.BookDaoImpl;
import by.itacademy.elegantsignal.marketplace.service.IBookService;

public class BookServiceImpl implements IBookService {

	private IBookDao dao = new BookDaoImpl();

	@Override
	public IBook createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IBook entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IBook get(final Integer id) {
		final IBook entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<IBook> getAll() {
		final List<IBook> all = dao.selectAll();
		return all;
	}

}
