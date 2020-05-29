package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;


public interface IBook extends IBaseEntity {

	IProduct getProduct();

	IBook setProduct(IProduct product);

	String getTitle();

	IBook setTitle(String title);

	String getAuthor();

	IBook setAuthor(String author);

	File getCover();

	IBook setCover(File file);

	LocalDate getPublished();

	IBook setPublished(LocalDate published);

	String getDescription();

	IBook setDescription(String description);

	Date getCreated();

	IBook setCreated(Date created);

	Date getUpdated();

	IBook setUpdated(Date update);

	List<IGenre> getGenre();

	IBook setGenre(List<IGenre> genre);

	File getPdf();

	IBook setPdf(File file);

}
