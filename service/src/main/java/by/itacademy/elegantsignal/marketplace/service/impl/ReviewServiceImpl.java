package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IReviewDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;
import by.itacademy.elegantsignal.marketplace.service.IReviewService;


@Service
public class ReviewServiceImpl implements IReviewService {

	private final IReviewDao reviewDao;

	@Autowired
	public ReviewServiceImpl(final IReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	public IReview createEntity() {
		return reviewDao.createEntity();
	}

	@Override
	public void save(final IReview entity) {
		final Date modifiedOn = new Date();
		entity.setUpdated(modifiedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			reviewDao.insert(entity);
		} else {
			reviewDao.update(entity);
		}
	}

	@Override
	public IReview get(final Integer id) {
		return reviewDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		reviewDao.delete(id);
	}

	@Override
	public void deleteAll() {
		reviewDao.deleteAll();
	}

	@Override
	public List<IReview> getAll() {
		return reviewDao.selectAll();
	}

	@Override
	@Deprecated
	public void saveWithId(final IReview review) {
		final Date modifiedOn = new Date();
		review.setUpdated(modifiedOn);
		review.setCreated(modifiedOn);
		reviewDao.insert(review);
	}
}
