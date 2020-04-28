package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Book;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Book_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.User;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.User_;
import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
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
	public IBook getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IBook> cq = cb.createQuery(IBook.class); // define returning result
		final Root<Book> from = cq.from(Book.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Book_.product, JoinType.LEFT).fetch(Product_.user, JoinType.LEFT);
		from.fetch(Book_.genre, JoinType.LEFT);
		// .. where id=...
		// TODO: check distinct=false
		cq.distinct(true);
		cq.where(cb.equal(from.get(Book_.id), id)); // where id=?

		final TypedQuery<IBook> q = em.createQuery(cq);

		return q.getSingleResult();
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

	@Override
	public void delete(final Integer id) {
		final EntityManager entityManager = getEntityManager();
		entityManager.createQuery(String.format("delete from %s e where e.id = :id", Product.class.getSimpleName()))
				.setParameter("id", id).executeUpdate();
	}

	@Override
	public List<IBook> find(final BookFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IBook> cq = cb.createQuery(IBook.class);
		final Root<Book> from = cq.from(Book.class);
		cq.select(from);

		from.fetch(Book_.product, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

		// set sort params
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, filter.getSortColumn());
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IBook> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private void applyFilter(final BookFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Book> from) {
		final List<Predicate> ands = new ArrayList<>();

		final IUser user = filter.getUser();
		if (filter.getUser() != null) {
			ands.add(cb.equal(from.get(Book_.product).get(Product_.user).get(User_.id), user.getId()));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	private Path<?> getSortPath(final Root<Book> from, final String sortColumn) {
		switch (sortColumn) {
			case "id":
				return from.get(Book_.id);

			case "user":
				return from.get(Book_.product).get(Product_.user).get(User_.name);

			case "title":
				return from.get(Book_.title);

			case "created":
				return from.get(Book_.created);
			case "updated":
				return from.get(Book_.updated);
			default:
				throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public void insert(final IBook book) {

		final EntityManager entityManager = getEntityManager();
		entityManager.persist(book);
	}

	@Override
	public void update(final IBook book) {

		final EntityManager entityManager = getEntityManager();
		entityManager.merge(book);
		entityManager.flush();
	}

}
