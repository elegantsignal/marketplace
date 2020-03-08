package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;


public interface ILikeService {

	ILike get(Integer id);

	List<ILike> getAll();

	void save(ILike entity);

	void delete(Integer id);

	void deleteAll();

	ILike createEntity();

}
