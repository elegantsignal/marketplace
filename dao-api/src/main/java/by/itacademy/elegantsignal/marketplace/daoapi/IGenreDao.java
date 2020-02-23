package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;

public interface IGenreDao extends IDao<IGenre, Integer> {

	List<IGenre> find(GenreFilter filter);

	long getCount(GenreFilter filter);
}
