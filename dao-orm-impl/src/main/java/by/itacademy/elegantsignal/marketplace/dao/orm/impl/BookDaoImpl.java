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

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.BaseEntity_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Book;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Book_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.User;
import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;


@Repository
public class BookDaoImpl extends AbstractDaoImpl<IBook, Integer> implements IBookDao {

	protected BookDaoImpl() {
		super(Book.class);
	}

	@Override
	public IBook createEntity() {
		return new Book();
	}

	@Override
	public List<IBook> find(final BookFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IBook> cq = cb.createQuery(IBook.class);
		final Root<Book> from = cq.from(Book.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Book, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IBook> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Book, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
			case "id":
				return BaseEntity_.id;
			case "title":
				return Book_.title;
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(final BookFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<User> from = cq.from(User.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
