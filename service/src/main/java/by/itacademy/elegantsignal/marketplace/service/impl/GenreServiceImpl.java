package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IGenreDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;


@Service
public class GenreServiceImpl implements IGenreService {

	@Autowired
	private IGenreDao genreDao;

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

}
