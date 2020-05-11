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
	private String author;
	private String title;
	private Set<Integer> genreIds;
	private String description;
	private String cover;
	private BigDecimal price;
	@DateTimeFormat(pattern = "yyyy-MM-dd") private LocalDate published;
	private Date created;
	private Date updated;

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

	public void setPublished(final LocalDate localDate) {
		this.published = localDate;
	}

}
