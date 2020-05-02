package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class OrderToDTOConverter implements Function<IOrder, OrderDTO> {

	@Override
	public OrderDTO apply(final IOrder order) {
		final OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setStatus(order.getStatus().toString());
		orderDTO.setCreated(order.getCreated());
		orderDTO.setUpdated(order.getUpdated());
		return orderDTO;
	}

}