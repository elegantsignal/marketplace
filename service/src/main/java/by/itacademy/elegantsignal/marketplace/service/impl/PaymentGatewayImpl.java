package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.service.IPaymentGateway;
import org.springframework.stereotype.Service;


@Service
public class PaymentGatewayImpl implements IPaymentGateway {

	// TODO: Implement pyament process
	@Override public OrderStatus process(final IOrder order) {
		return OrderStatus.PAYED;
	}
}
