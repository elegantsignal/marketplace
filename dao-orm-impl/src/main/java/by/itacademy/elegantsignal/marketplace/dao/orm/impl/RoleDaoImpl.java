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
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Role;
import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Role_;
import by.itacademy.elegantsignal.marketplace.daoapi.IRoleDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.RoleName;
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

	@Override
	public IRole findOne(final RoleFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IRole> cq = cb.createQuery(IRole.class);
		final Root<Role> from = cq.from(Role.class);
		cq.select(from);

		applyFilter(filter, cb, cq, from);

		final TypedQuery<IRole> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private void applyFilter(final RoleFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Role> from) {
		final List<Predicate> ands = new ArrayList<>();

		final RoleName name = filter.getName();
		if (name != null) {
			ands.add(cb.equal(from.get(Role_.name), name));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public IRole getRoleByName(String name) {
		RoleFilter filter = new RoleFilter();
		filter.setName(name);
		return findOne(filter);
		
	}

}
