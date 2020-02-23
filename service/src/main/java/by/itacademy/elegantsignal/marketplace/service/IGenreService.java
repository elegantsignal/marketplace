package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;

public interface IGenreService {

	IGenre get(Integer id);

	List<IGenre> getAll();

	void save(IGenre entity);

	void delete(Integer id);

	void deleteAll();

	IGenre createEntity();

}
