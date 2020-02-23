package by.itacademy.elegantsignal.marketplace.daojdc.entity;

import java.nio.file.Path;
import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;

public class Book extends BaseEntity implements IBook {

	private IProduct product;
	private String title;
	private Path cover;
	private Date published;
	private String description;
	private Date created;
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
