package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;


public interface IDownload extends IBaseEntity {

	IOrderItem getOrderItem();

	void setOrderItem(IOrderItem orderItem);

	String getToken();

	void setToken(String token);

	void setCreated(Date created);

	Date getCreated();
}
