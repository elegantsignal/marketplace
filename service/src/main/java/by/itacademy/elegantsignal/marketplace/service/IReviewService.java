package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;


public interface IReviewService {

	IReview get(Integer id);

	List<IReview> getAll();

	void save(IReview entity);

	void delete(Integer id);

	void deleteAll();

	IReview createEntity();

	@Deprecated
	void saveWithId(IReview review);
}
