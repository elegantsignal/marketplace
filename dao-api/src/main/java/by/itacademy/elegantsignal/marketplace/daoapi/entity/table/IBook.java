package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;


public interface IBook extends IBaseEntity {

	IProduct getProduct();

	void setProduct(IProduct product);

	String getTitle();

	void setTitle(String title);

	String getCover();

	void setCover(String cover);

	Date getPublished();

	void setPublished(Date published);

	String getDescription();

	void setDescription(String description);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

}
