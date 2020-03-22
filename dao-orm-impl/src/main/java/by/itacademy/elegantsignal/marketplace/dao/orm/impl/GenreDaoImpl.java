package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

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
				return Genre_.id;
			case "name":
				return Genre_.name;
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
