package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Book;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Book_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Product_;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.User_;
import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.jpa.criteria.OrderImpl;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
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
public class BookDaoImpl extends AbstractDaoImpl<IBook, Integer> implements IBookDao {

	@PersistenceContext private EntityManager entityManager;

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

		final CriteriaQuery<IBook> cq = cb.createQuery(IBook.class);
		final Root<Book> from = cq.from(Book.class);

		cq.select(from);

		from.fetch(Book_.product, JoinType.LEFT).fetch(Product_.user, JoinType.LEFT);
		from.fetch(Book_.genre, JoinType.LEFT);

		cq.distinct(true); // TODO: check distinct=false
		cq.where(cb.equal(from.get(Book_.id), id));

		final TypedQuery<IBook> q = em.createQuery(cq);

		return q.getSingleResult();
	}

	@Override
	public long getCount(final BookFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Book> from = cq.from(Book.class);
		cq.select(cb.count(from));
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<IBook> find(final BookFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IBook> cq = cb.createQuery(IBook.class);
		final Root<Book> from = cq.from(Book.class);
		cq.select(from);

		from.fetch(Book_.product, JoinType.LEFT).fetch(Product_.user, JoinType.LEFT);
		from.fetch(Book_.genre, JoinType.LEFT);

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

	private void applyFilter(final BookFilter filter, final CriteriaBuilder criteriaBuilder, final CriteriaQuery<?> criteriaQuery,
		final Root<Book> from) {

		final List<Predicate> ands = new ArrayList<>();

		if (filter.getUser() != null) {
			ands.add(criteriaBuilder.equal(from.get(Book_.product).get(Product_.user).get(User_.id), filter.getUser().getId()));
		}

		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
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

	@Override
	public List<IBook> search(final String queryString) {
		final EntityManager entityManager = getEntityManager();
		final FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
		final QueryBuilder builder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();

		final org.apache.lucene.search.Query luceneQuery = builder.keyword().onFields("author", "title").matching(queryString).createQuery();

		final Session session = (Session) fullTextEntityManager.getDelegate();
		final Criteria criteria = session.createCriteria(Book.class)
			.setFetchMode("product", FetchMode.JOIN)
			.setFetchMode("product.user", FetchMode.JOIN);
		final javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery).setCriteriaQuery(criteria);
		return jpaQuery.setMaxResults(3).getResultList();
	}

	@Override public List<IBook> getBooksByGenres(final List<String> genres) {
		final TypedQuery<IBook> query = entityManager.createQuery(String.join(
			" ",
			"select distinct book from Book book",
			"join fetch book.product product",
			"join fetch product.user user",
			"join fetch book.genre genre",
			"where genre.name in :genres"
		), IBook.class);

		query.setParameter("genres", genres);

		return query.getResultList();
	}

	@Override public List<IBook> getBooksByAuthorId(final List<Integer> ids) {
		final TypedQuery<IBook> query = entityManager.createQuery(String.join(
			" ",
			"select distinct book from Book book",
			"join fetch book.product product",
			"join fetch product.user user",
			"where user.id in :ids"
		), IBook.class);

		query.setParameter("ids", ids);

		return query.getResultList();
	}

}
