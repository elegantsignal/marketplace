package by.itacademy.elegantsignal.marketplace.daoapi.filter;

public class OrderItemFilter extends AbstractFilter {

	private Integer id;
	private Integer orderId;

	public Integer getId() {
		return id;
	}

	public OrderItemFilter setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public OrderItemFilter setOrderId(Integer orderId) {
		this.orderId = orderId;
		return this;
	}

}
