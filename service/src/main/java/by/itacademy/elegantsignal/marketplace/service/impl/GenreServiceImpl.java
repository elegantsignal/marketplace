package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IGenreDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;


@Service
public class GenreServiceImpl implements IGenreService {

	private final IGenreDao genreDao;

	@Autowired
	public GenreServiceImpl(final IGenreDao genreDao) {
		this.genreDao = genreDao;
	}

	@Override
	public IGenre createEntity() {
		return genreDao.createEntity();
	}

	@Override
	public void save(final IGenre entity) {
		if (entity.getId() == null) {
			genreDao.insert(entity);
		} else {
			genreDao.update(entity);
		}
	}

	@Override
	public IGenre get(final Integer id) {
		return genreDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		genreDao.delete(id);
	}

	@Override
	public void deleteAll() {
		genreDao.deleteAll();
	}

	@Override
	public List<IGenre> getAll() {
		return genreDao.selectAll();
	}

	@Override
	@Deprecated
	public void saveWithId(final IGenre genre) {
		genreDao.insert(genre);
	}

	@Override
	public List<IGenre> find(final GenreFilter filter) {
		return genreDao.find(filter);
	}

	@Override
	public long getCount(final GenreFilter filter) {
		return genreDao.getCount(filter);
	}

	@Override
	public IGenre findOne(GenreFilter genreFilter) {
		return genreDao.findOne(genreFilter);
	}

}
