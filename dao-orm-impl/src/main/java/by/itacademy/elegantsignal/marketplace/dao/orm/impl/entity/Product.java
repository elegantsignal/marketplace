package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Date;


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
	public IProduct setUser(final IUser user) {
		this.user = user;
		return this;
	}

	@Override
	public ProductType getType() {
		return type;
	}

	@Override
	public IProduct setType(final ProductType type) {
		this.type = type;
		return this;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public IProduct setPrice(final BigDecimal price) {
		this.price = price;
		return this;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public IProduct setCreated(final Date created) {
		this.created = created;
		return this;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public IProduct setUpdated(final Date updated) {
		this.updated = updated;
		return this;
	}

	@Override
	public IBook getBook() {
		return book;
	}

	@Override
	public IProduct setBook(final IBook book) {
		this.book = book;
		return this;
	}

}
