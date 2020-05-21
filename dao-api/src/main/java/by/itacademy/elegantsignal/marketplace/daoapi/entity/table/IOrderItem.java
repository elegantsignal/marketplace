package by.itacademy.elegantsignal.marketplace.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.List;


public interface IOrderItem extends IBaseEntity {

	IOrder getOrder();

	IOrderItem setOrder(IOrder order);

	IProduct getProduct();

	IOrderItem setProduct(IProduct product);

	BigDecimal getAmount();

	IOrderItem setAmount(BigDecimal amount);

	List<IDownload> getDownloadList();

	IOrderItem setDownloadLink(List<IDownload> downloadLink);
}
