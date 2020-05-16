package by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;


@Entity
public class OrderItem extends BaseEntity implements IOrderItem {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	private IOrder order;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
	private IProduct product;

	@Column
	private BigDecimal amount;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Download.class)
	@JoinColumn(name = "order_item_id")
	private List<IDownload> downloadList;

	@Override public IOrder getOrder() {
		return order;
	}

	@Override public IOrderItem setOrder(final IOrder order) {
		this.order = order;
		return this;
	}

	@Override public IProduct getProduct() {
		return product;
	}

	@Override public IOrderItem setProduct(final IProduct product) {
		this.product = product;
		return this;
	}

	@Override public BigDecimal getAmount() {
		return amount;
	}

	@Override public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	@Override public List<IDownload> getDownloadList() {
		return downloadList;
	}

	@Override public IOrderItem setDownloadLink(final List<IDownload> downloadLink) {
		this.downloadList = downloadLink;
		return this;
	}
}
