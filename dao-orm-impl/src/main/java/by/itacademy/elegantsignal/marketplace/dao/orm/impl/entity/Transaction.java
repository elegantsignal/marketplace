package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;


@Entity @Getter @Setter @Accessors(chain = true)
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

}
