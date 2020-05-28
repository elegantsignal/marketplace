package by.itacademy.elegantsignal.marketplace.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class BookDTO {

	private Integer id;
	private Integer productOwnerId;
	private String author;
	private String title;
	private Set<Integer> genreIds;
	private String description;
	private String cover;
	private String pdf;
	private BigDecimal price;
	@DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate published;
	private Date created;
	private Date updated;

	public void setPublished(final LocalDate localDate) {
		published = localDate;
	}

	public void setCover(final String cover) {
		this.cover = cover;
	}

	public void setCover(final File cover) {
		if (cover != null) {
			setCover(cover.toString());
			return;
		}
		setCover("");
	}

	public void setPdf(final String pdf) {
		this.pdf = pdf;
	}

	public void setPdf(final File pdf) {
		if (pdf != null) {
			setPdf(pdf.toString());
			return;
		}
		setPdf("");
	}
}
