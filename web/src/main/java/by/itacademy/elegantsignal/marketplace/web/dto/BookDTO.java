package by.itacademy.elegantsignal.marketplace.web.dto;

import java.nio.file.Path;
import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;

public class BookDTO {

	private Integer id;
	private IProduct product;
	private String title;
	private Path cover;
	private Date published;
	private String description;
	private Date created;
	private Date updated;

	public IProduct getProduct() {
		return product;
	}

	public void setProduct(IProduct product) {
		this.product = product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Path getCover() {
		return cover;
	}

	public void setCover(Path cover) {
		this.cover = cover;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

}
