package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILike;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


@Entity(name = "Like")
@javax.persistence.Table(name = "`like`")
public class Like extends BaseEntity implements ILike {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private IUser user;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;

	@Column
	private Date created;

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(final IUser user) {
		this.user = user;
	}

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(final IProduct product) {
		this.product = product;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = created;
	}

}
