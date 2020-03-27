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
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Role;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Role_;
import by.itacademy.elegantsignal.marketplace.daoapi.IRoleDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.RoleFilter;


@Repository
public class RoleDaoImpl extends AbstractDaoImpl<IRole, Integer> implements IRoleDao {

	protected RoleDaoImpl() {
		super(Role.class);
	}

	@Override
	public IRole createEntity() {
		return new Role();
	}

	@Override
	public long getCount(final RoleFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define type of result
		final Root<Role> from = cq.from(Role.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<IRole> find(final RoleFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IRole> cq = cb.createQuery(IRole.class); // define type of result
		final Root<Role> from = cq.from(Role.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Role, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to sort property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order by column_name order
		}

		final TypedQuery<IRole> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Role, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return BaseEntity_.id;
		case "name":
			return Role_.name;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
