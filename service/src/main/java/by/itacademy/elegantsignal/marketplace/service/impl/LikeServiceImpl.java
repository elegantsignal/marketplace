package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.ILikeDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.service.ILikeService;


@Service
public class LikeServiceImpl implements ILikeService {
	private ILikeDao likeDao;

	@Autowired
	public LikeServiceImpl(ILikeDao likeDao) {
		this.likeDao = likeDao;
	}

	@Override
	public ILike createEntity() {
		return likeDao.createEntity();
	}

	@Override
	public void save(final ILike entity) {
		final Date modifiedOn = new Date();
		if (entity.getId() == null) {
			entity.setCreated(modifiedOn);
			likeDao.insert(entity);
		} else {
			throw new RuntimeException("Update for Like filed unawalible. Only delete and create");
		}
	}

	@Override
	public ILike get(final Integer id) {
		final ILike entity = likeDao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		likeDao.delete(id);
	}

	@Override
	public void deleteAll() {
		likeDao.deleteAll();
	}

	@Override
	public List<ILike> getAll() {
		final List<ILike> all = likeDao.selectAll();
		return all;
	}

}
