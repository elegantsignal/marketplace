package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.dao.orm.converter.FileAtributeConverter;
import by.itacademy.elegantsignal.marketplace.dao.orm.converter.LocalDateAttributeConverter;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

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
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Indexed
public class Book implements IBook {

	@Id
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class)
	@PrimaryKeyJoinColumn
	private IProduct product;

	@JoinTable(name = "book_2_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
	private Set<IGenre> genre = new HashSet<>();

	@Column
	private String title;

	@Column
	@Convert(converter = FileAtributeConverter.class)
	private File cover;

	@Column
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate published;

	@Column
	// TODO: Enable search
	@Field(index = Index.YES, analyze =  Analyze.YES, store = Store.NO)
	private String description;

	@Column(updatable = false)
	private Date created;

	@Column
	private Date updated;

	@Column
	@Convert(converter = FileAtributeConverter.class)
	private File pdf;

	@Override public Integer getId() {
		return id;
	}

	@Override public void setId(final Integer id) {
		this.id = id;
	}

	@Override public Set<IGenre> getGenre() {
		return genre;
	}

	@Override public IBook setGenre(final Set<IGenre> genre) {
		this.genre = genre;
		return this;
	}

	@Override public File getPdf() {
		return pdf;
	}

	@Override public IBook setPdf(final File pdf) {
		this.pdf = pdf;
		return this;
	}

	@Override public IProduct getProduct() {
		return product;
	}

	@Override public IBook setProduct(final IProduct product) {
		this.product = product;
		return this;
	}

	@Override public String getTitle() {
		return title;
	}

	@Override public IBook setTitle(final String title) {
		this.title = title;
		return this;
	}

	@Override public File getCover() {
		return cover;
	}

	@Override public IBook setCover(final File cover) {
		this.cover = cover;
		return this;
	}

	@Override public LocalDate getPublished() {
		return published;
	}

	@Override public IBook setPublished(final LocalDate published) {
		this.published = published;
		return this;
	}

	@Override public String getDescription() {
		return description;
	}

	@Override public IBook setDescription(final String description) {
		this.description = description;
		return this;
	}

	@Override public Date getCreated() {
		return created;
	}

	@Override public IBook setCreated(final Date created) {
		this.created = created;
		return this;
	}

	@Override public Date getUpdated() {
		return updated;
	}

	@Override public IBook setUpdated(final Date updated) {
		this.updated = updated;
		return this;
	}

}
