package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


public class Book extends BaseEntity implements IBook {

	private IProduct product;
	private String title;
	private String cover;
	private Date published;
	private String description;
	private Date created;
	private Date updated;

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(final IProduct product) {
		this.product = product;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public String getCover() {
		return cover;
	}

	@Override
	public void setCover(final String cover) {
		this.cover = cover;
	}

	@Override
	public Date getPublished() {
		return published;
	}

	@Override
	public void setPublished(final Date published) {
		this.published = published;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
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

}
