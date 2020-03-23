package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Review;
import by.itacademy.elegantsignal.marketplace.daoapi.IReviewDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.ReviewFilter;


@Repository
public class ReviewDaoImpl extends AbstractDaoImpl<IReview, Integer> implements IReviewDao {

	protected ReviewDaoImpl() {
		super(Review.class);
	}

	@Override
	public IReview createEntity() {
		return new Review();
	}

	@Override
	public List<IReview> find(final ReviewFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: find(); Timestamp: 3:33:40 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: find(); Timestamp: 3:33:40 PM");
		// return null;
	}

	@Override
	public long getCount(final ReviewFilter filter) {
		// TODO Auto-generated method stub
		System.err.println("UNIMPLEMENTED: getCount(); Timestamp: 3:33:40 PM");
		throw new UnsupportedOperationException("UNIMPLEMENTED: getCount(); Timestamp: 3:33:40 PM");
		// return 0;
	}

}
