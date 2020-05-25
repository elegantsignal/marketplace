package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class Transaction extends BaseEntity implements ITransaction {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private IUser user;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	private IOrder order;

	@Column
	private BigDecimal amount;

	@Column @Enumerated(EnumType.STRING)
	private TransactionType type;

	@Column @Enumerated(EnumType.STRING)
	private TransactionStatus status;

	@Column
	private Date created;

	@Column
	private Date updated;

	@Override public IUser getUser() {
		return user;
	}

	@Override public Transaction setUser(final IUser user) {
		this.user = user;
		return this;
	}

	public IOrder getOrder() {
		return order;
	}

	public void setOrder(final IOrder order) {
		this.order = order;
	}

	@Override public BigDecimal getAmount() {
		return amount;
	}

	@Override public Transaction setAmount(final BigDecimal amount) {
		this.amount = amount;
		return this;
	}

	@Override public TransactionType getType() {
		return type;
	}

	@Override public Transaction setType(final TransactionType type) {
		this.type = type;
		return this;
	}

	@Override public TransactionStatus getStatus() {
		return status;
	}

	@Override public Transaction setStatus(final TransactionStatus status) {
		this.status = status;
		return this;
	}

	@Override public Date getCreated() {
		return created;
	}

	@Override public Transaction setCreated(final Date created) {
		this.created = created;
		return this;

	}

	@Override public Date getUpdated() {
		return updated;
	}

	@Override public Transaction setUpdated(final Date updated) {
		this.updated = updated;
		return this;
	}
}
