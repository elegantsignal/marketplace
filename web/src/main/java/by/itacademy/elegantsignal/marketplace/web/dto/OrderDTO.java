package by.itacademy.elegantsignal.marketplace.web.dto;

import java.util.Date;
import java.util.List;


public class OrderDTO {

	private Integer id;
	private String status;
	private Date created;
	private Date updated;
	private List<OrderItemDTO> orderItems;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(final List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
