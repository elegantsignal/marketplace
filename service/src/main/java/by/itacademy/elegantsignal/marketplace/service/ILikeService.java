package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;


public interface ILikeService {

	ILike get(Integer id);

	List<ILike> getAll();

	@Transactional
	void save(ILike entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	ILike createEntity();

}
