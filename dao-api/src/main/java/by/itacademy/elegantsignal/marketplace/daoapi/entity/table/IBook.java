package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


public interface IBook extends IBaseEntity {

	IProduct getProduct();

	void setProduct(IProduct product);

	String getTitle();

	void setTitle(String title);

	String getCover();

	void setCover(String cover);

	LocalDate getPublished();

	void setPublished(LocalDate published);

	String getDescription();

	void setDescription(String description);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

	Set<IGenre> getGenre();

	void setGenre(Set<IGenre> genre);

}
