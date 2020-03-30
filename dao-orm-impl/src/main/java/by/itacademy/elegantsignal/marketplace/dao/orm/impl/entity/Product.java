package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


@Entity
public class Product extends BaseEntity implements IProduct {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	private IUser user;

	@Column
	@Enumerated(EnumType.STRING)
	private ProductType type;

	@Column
	private BigDecimal price;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "product", targetEntity = Book.class)
	private IBook book;

	@Column
	private Date created;

	@Column
	private Date updated;

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(final IUser user) {
		this.user = user;
	}

	@Override
	public ProductType getType() {
		return type;
	}

	@Override
	public void setType(final ProductType type) {
		this.type = type;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	@Override
	public IBook getBook() {
		return book;
	}

	@Override
	public void setBook(final IBook book) {
		this.book = book;
	}

}
