package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.nio.file.Path;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import by.itacademy.elegantsignal.marketplace.dao.orm.converter.PathConverter;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


@Entity
public class Book extends BaseEntity implements IBook {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;

	@JoinTable(name = "book_2_genre", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "genre_id") })
	@ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
	private Set<IGenre> genre = new HashSet<>();

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

	public Set<IGenre> getGenre() {
		return genre;
	}

	public void setGenre(final Set<IGenre> genre) {
		this.genre = genre;
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
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public Path getCover() {
		return cover;
	}

	@Override
	public void setCover(final Path cover) {
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
