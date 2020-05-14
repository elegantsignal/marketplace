package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;


public interface IDownload extends IBaseEntity {

	IOrderItem getOrderItem();

	IDownload setOrderItem(IOrderItem orderItem);

	String getToken();

	IDownload setToken(String token);

	IDownload setCreated(Date created);

	Date getCreated();
}
