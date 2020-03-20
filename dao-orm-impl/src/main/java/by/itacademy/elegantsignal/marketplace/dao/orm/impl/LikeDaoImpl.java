package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Like;
import by.itacademy.elegantsignal.marketplace.daoapi.ILikeDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.LikeFilter;


@Repository
public class LikeDaoImpl extends AbstractDaoImpl<ILike, Integer> implements ILikeDao {

	protected LikeDaoImpl() {
		super(Like.class);
	}

	@Override
	public ILike createEntity() {
		return new Like();
	}

	@Override
	public List<ILike> find(final LikeFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(final LikeFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}
