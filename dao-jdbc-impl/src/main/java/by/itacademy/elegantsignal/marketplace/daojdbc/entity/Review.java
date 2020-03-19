package by.itacademy.elegantsignal.marketplace.daojdbc.entity;

import java.util.Date;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IReview;


public class Review extends BaseEntity implements IReview {

	private IOrderItem orderItem;

	private Integer grade;
	private String comment;

	private Date created;
	private Date updated;

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(final Date created) {
		this.created = created;
	}

	@Override
	public Date getUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	@Override
	public IOrderItem getOrderItem() {
		return this.orderItem;
	}

	@Override
	public void setOrderItem(final IOrderItem orderItem) {
		this.orderItem = orderItem;

	}

	@Override
	public Integer getGrade() {
		return this.grade;
	}

	@Override
	public void setGrade(final Integer grade) {
		this.grade = grade;

	}

	@Override
	public String getComment() {
		return this.comment;
	}

	@Override
	public void setComment(final String comment) {
		this.comment = comment;
	}
}
