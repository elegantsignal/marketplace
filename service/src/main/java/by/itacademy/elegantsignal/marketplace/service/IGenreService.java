package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;


public interface IGenreService {

	IGenre get(Integer id);

	List<IGenre> getAll();

	@Transactional
	void save(IGenre entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IGenre createEntity();

	@Transactional
	@Deprecated
	void saveWithId(IGenre genre);

	List<IGenre> find(GenreFilter filter);

	long getCount(GenreFilter filter);

}
