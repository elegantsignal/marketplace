package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IGenreDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daojdc.GenreDaoImpl;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;

@Service
public class GenreServiceImpl implements IGenreService {

	private IGenreDao dao = new GenreDaoImpl();

	@Override
	public IGenre createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IGenre entity) {
		if (entity.getId() == null) {
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IGenre get(final Integer id) {
		return dao.get(id);
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
	public List<IGenre> getAll() {
		return dao.selectAll();
	}

}
