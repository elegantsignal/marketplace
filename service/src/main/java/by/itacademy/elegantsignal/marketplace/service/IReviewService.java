package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import javax.transaction.Transactional;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;


public interface IReviewService {

	IReview get(Integer id);

	List<IReview> getAll();

	@Transactional
	void save(IReview entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	@Transactional
	IReview createEntity();

	@Deprecated
	@Transactional
	void saveWithId(IReview review);

	IReview getFullInfo(Integer id);
}
