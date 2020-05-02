package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class OrderItemToDTOConverter implements Function<IOrderItem, OrderItemDTO> {

	@Override
	public OrderItemDTO apply(final IOrderItem orderItem) {
		final OrderItemDTO orderItemDTO = new OrderItemDTO();
		orderItemDTO.setId(orderItem.getId());
		orderItemDTO.setProductTitle(orderItem.getProduct().getBook().getTitle());
		orderItemDTO.setAmount(orderItem.getProduct().getPrice());
		return orderItemDTO;
	}

}

