package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.service.IDownloadService;
import by.itacademy.elegantsignal.marketplace.service.IOrderService;
import by.itacademy.elegantsignal.marketplace.service.IPaymentGateway;
import by.itacademy.elegantsignal.marketplace.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements IPaymentService {

	@Autowired private IOrderService orderService;
	@Autowired private IPaymentGateway paymentGateway;
	@Autowired private IDownloadService downloadService;

	@Override public IOrder checkout(final IOrder order) {
		final OrderStatus orderStatus = paymentGateway.process(order);
		orderService.setStatus(order, orderStatus);
		if (order.getStatus().equals(OrderStatus.PAYED)) {
			order.getOrderItems().forEach(orderItem -> downloadService.save(
				downloadService.createEntity().setOrderItem(orderItem))
			);
		}
		return order;
	}
}
