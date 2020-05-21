package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Transaction;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Transaction_;
import by.itacademy.elegantsignal.marketplace.daoapi.ITransactionDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.TransactionFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<ITransaction, Integer> implements ITransactionDao {

	@PersistenceContext private EntityManager entityManager;

	protected TransactionDaoImpl() {
		super(Transaction.class);
	}

	@Override public ITransaction createEntity() {
		return new Transaction();
	}

	@Override public ITransaction findOne(final TransactionFilter filter) {
		return find(filter).getSingleResult();
	}

	@Override public List<ITransaction> findAll(final TransactionFilter filter) {
		return find(filter).getResultList();
	}

	private TypedQuery<ITransaction> find(final TransactionFilter filter) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<ITransaction> criteriaQuery = criteriaBuilder.createQuery(ITransaction.class);
		final Root<Transaction> from = criteriaQuery.from(Transaction.class);
		criteriaQuery.select(from);
		applyFilter(filter, criteriaBuilder, criteriaQuery, from);
		return entityManager.createQuery(criteriaQuery);
	}

	private <T> void applyFilter(final TransactionFilter filter, final CriteriaBuilder criteriaBuilder, final CriteriaQuery<T> criteriaQuery, final Root<Transaction> from) {
		final List<Predicate> ands = new ArrayList<>();

		if (filter.getId() != null) {
			ands.add(criteriaBuilder.equal(from.get(Transaction_.id), filter.getId()));
		}
		if (filter.getUserId() != null) {
			ands.add(criteriaBuilder.equal(from.get(Transaction_.user), filter.getUserId()));
		}
		if (filter.getType() != null) {
			ands.add(criteriaBuilder.equal(from.get(Transaction_.type), filter.getType()));
		}
		if (filter.getStatus() != null) {
			ands.add(criteriaBuilder.equal(from.get(Transaction_.status), filter.getStatus()));
		}
		if (!ands.isEmpty()) {
			criteriaQuery.where(criteriaBuilder.and(ands.toArray(new Predicate[0])));
		}
	}

}
