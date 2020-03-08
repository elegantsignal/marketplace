package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.LikeFilter;


public interface ILikeDao extends IDao<ILike, Integer> {

	List<ILike> find(LikeFilter filter);

	long getCount(LikeFilter filter);
}
