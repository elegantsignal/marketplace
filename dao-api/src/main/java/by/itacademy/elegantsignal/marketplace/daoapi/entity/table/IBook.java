package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.nio.file.Path;
import java.util.Date;

public interface IBook extends IBaseEntity {

	IProduct getProduct();

	void setProduct(IProduct product);

	String getTitle();

	void setTitle(String title);

	Path getCover();

	void setCover(Path cover);

	Date getPublished();

	void setPublished(Date published);

	String getDescription();

	void setDescription(String description);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

}
