package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import by.itacademy.elegantsignal.marketplace.dao.orm.converter.LocalDateAttributeConverter;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;


@Entity
public class Book implements IBook {

	@Id
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
	@PrimaryKeyJoinColumn
	private IProduct product;

	@JoinTable(name = "book_2_genre", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
			@JoinColumn(name = "genre_id") })
	@ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
	private Set<IGenre> genre = new HashSet<>();

	@Column
	private String title;

	@Column
	private String cover;

	@Column
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate published;

	@Column
	private String description;

	@Column
	private Date created;

	@Column
	private Date updated;

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

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
	public String getCover() {
		return cover;
	}

	@Override
	public void setCover(final String cover) {
		this.cover = cover;
	}

	@Override
	public LocalDate getPublished() {
		return published;
	}

	@Override
	public void setPublished(final LocalDate published) {
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
