package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Download;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Download_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.OrderItem_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Order_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.User;
import by.itacademy.elegantsignal.marketplace.daoapi.IDownloadDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.DownloadFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class DownloadDaoImpl extends AbstractDaoImpl<IDownload, Integer> implements IDownloadDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected DownloadDaoImpl() {
		super(Download.class);
	}

	@Override public IDownload createEntity() {
		return new Download();
	}

	@Override public List<IDownload> find(final DownloadFilter filter) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<IDownload> criteriaQuery = criteriaBuilder.createQuery(IDownload.class);
		final Root<Download> from = criteriaQuery.from(Download.class);
		criteriaQuery.select(from);

		from.fetch(Download_.orderItem, JoinType.LEFT)
			.fetch(OrderItem_.order, JoinType.LEFT)
			.fetch(Order_.user, JoinType.LEFT);

		from.fetch(Download_.orderItem, JoinType.LEFT)
			.fetch(OrderItem_.product, JoinType.LEFT)
			.fetch(Product_.book, JoinType.LEFT);

		applyFilter(filter, criteriaBuilder, criteriaQuery, from);

		final TypedQuery<IDownload> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	private void applyFilter(
		final DownloadFilter filter,
		final CriteriaBuilder criteriaBuilder,
		final CriteriaQuery<IDownload> criteriaQuery,
		final Root<Download> from) {

		final List<Predicate> ands = new ArrayList<>();

		if (filter.getUserId() != null) {
			final Path<User> userPath = from
				.get(Download_.orderItem)
				.get(OrderItem_.order)
				.get(Order_.user);
			ands.add(criteriaBuilder.equal(userPath, filter.getUserId()));
		}

		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
		}
	}
}
