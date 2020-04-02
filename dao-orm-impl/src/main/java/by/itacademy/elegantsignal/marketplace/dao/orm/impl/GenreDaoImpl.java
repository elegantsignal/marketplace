package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.BaseEntity_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Genre;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Genre_;
import by.itacademy.elegantsignal.marketplace.daoapi.IGenreDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;


@Repository
public class GenreDaoImpl extends AbstractDaoImpl<IGenre, Integer> implements IGenreDao {

	protected GenreDaoImpl() {
		super(Genre.class);
	}

	@Override
	public IGenre createEntity() {
		return new Genre();
	}

	@Override
	public long getCount(final GenreFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Genre> from = cq.from(Genre.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<IGenre> find(final GenreFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IGenre> cq = cb.createQuery(IGenre.class); // define type of result
		final Root<Genre> from = cq.from(Genre.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Genre, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to sort property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<IGenre> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Genre, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
			case "id":
				return BaseEntity_.id;
			case "name":
				return Genre_.name;
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IGenre findOne(final GenreFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IGenre> cq = cb.createQuery(IGenre.class);
		final Root<Genre> from = cq.from(Genre.class);
		cq.select(from);

		applyFilter(filter, cb, cq, from);

		final TypedQuery<IGenre> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private void applyFilter(final GenreFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Genre> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String genreName = filter.getName();
		if (genreName != null) {
			ands.add(cb.equal(from.get(Genre_.name), genreName));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}
}
