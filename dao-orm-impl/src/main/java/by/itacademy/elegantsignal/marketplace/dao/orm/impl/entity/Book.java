package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.nio.file.Path;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.itacademy.elegantsignal.marketplace.dao.orm.converter.PathConverter;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;

@Entity
public class Book extends BaseEntity implements IBook {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;

	@Column
	private String title;

	@Column
	@Convert(converter = PathConverter.class)
	private Path cover;

	@Column
	private Date published;

	@Column
	private String description;

	@Column
	private Date created;

	@Column
	private Date updated;

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IProduct product) {
		this.product = product;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Path getCover() {
		return cover;
	}

	@Override
	public void setCover(Path cover) {
		this.cover = cover;
	}

	@Override
	public Date getPublished() {
		return published;
	}

	@Override
	public void setPublished(Date published) {
		this.published = published;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
