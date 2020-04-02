package by.itacademy.elegantsignal.marketplace.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;


public class BookDTO {

	private Integer id;
	private BigDecimal price;
	private Set<IGenre> genre;
	private String title;
	private String cover;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate published;

	private String description;
	private Date created;
	private Date updated;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(final String cover) {
		this.cover = cover;
	}

	public LocalDate getPublished() {
		return published;
	}

	public void setPublished(final LocalDate localDate) {
		this.published = localDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public Set<IGenre> getGenre() {
		return this.genre;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

}
