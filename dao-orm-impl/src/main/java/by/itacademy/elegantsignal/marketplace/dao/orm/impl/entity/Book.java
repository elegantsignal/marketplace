package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.dao.orm.converter.FileAtributeConverter;
import by.itacademy.elegantsignal.marketplace.dao.orm.converter.LocalDateAttributeConverter;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// README: https://docs.jboss.org/hibernate/search/4.2/reference/en-US/html/search-query.html
@Entity @Indexed @AnalyzerDef(name = "ngram",
	tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
	filters = {
		@TokenFilterDef(factory = StandardFilterFactory.class),
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = StopFilterFactory.class),
		@TokenFilterDef(factory = NGramFilterFactory.class,
			params = {
				@Parameter(name = "minGramSize", value = "3"),
				@Parameter(name = "maxGramSize", value = "5") })
	}
)
public class Book implements IBook {

	@Id
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Product.class) @PrimaryKeyJoinColumn
	private IProduct product;

	@JoinTable(name = "book_2_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@ManyToMany(targetEntity = Genre.class, fetch = FetchType.LAZY)
	private List<IGenre> genre = new ArrayList<>();

	@Column @Field(analyzer = @Analyzer(definition = "ngram"))
	private String title;

	@Column @Field(analyzer = @Analyzer(definition = "ngram"))
	private String author;

	@Column @Convert(converter = FileAtributeConverter.class)
	private File cover;

	@Column @Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate published;

	@Column
	private String description;

	@Column(updatable = false)
	private Date created;

	@Column
	private Date updated;

	@Column @Convert(converter = FileAtributeConverter.class)
	private File pdf;

	@Override public Integer getId() {
		return id;
	}

	@Override public void setId(final Integer id) {
		this.id = id;
	}

	@Override public List<IGenre> getGenre() {
		return genre;
	}

	@Override public IBook setGenres(final List<IGenre> genre) {
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

	@Override public String getAuthor() {
		return author;
	}

	@Override public IBook setAuthor(final String author) {
		this.author = author;
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
