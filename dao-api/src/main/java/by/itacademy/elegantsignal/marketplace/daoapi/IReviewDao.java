package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.ReviewFilter;


public interface IReviewDao extends IDao<IReview, Integer> {

	List<IReview> find(ReviewFilter filter);

	long getCount(ReviewFilter filter);
}
