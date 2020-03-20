package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;


public interface IGenreService {

	IGenre get(Integer id);

	List<IGenre> getAll();

	void save(IGenre entity);

	void delete(Integer id);

	void deleteAll();

	IGenre createEntity();

	@Deprecated
	void saveWithId(IGenre genre);

	List<IGenre> find(GenreFilter filter);

	long getCount(GenreFilter filter);

}
