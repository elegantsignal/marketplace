package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.util.Date;


public interface IReview extends IBaseEntity {

	IOrderItem getOrderItem();

	void setOrderItem(IOrderItem orderItem);

	Integer getGrade();

	void setGrade(Integer grade);

	String getComment();

	void setComment(String comment);

	Date getCreated();

	void setCreated(Date created);

	Date getUpdated();

	void setUpdated(Date update);

}
