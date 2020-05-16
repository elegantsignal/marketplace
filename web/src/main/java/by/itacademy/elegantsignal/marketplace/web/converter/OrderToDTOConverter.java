package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Component
public class OrderToDTOConverter implements Function<IOrder, OrderDTO> {

	@Autowired private OrderItemToDTOConverter orderItemToDTOConverter;

	@Override
	public OrderDTO apply(final IOrder order) {
		final OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setStatus(order.getStatus().toString());
		orderDTO.setCreated(order.getCreated());
		orderDTO.setUpdated(order.getUpdated());

		final List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
		order.getOrderItems().forEach(orderItem ->
			orderItemDTOList.add(orderItemToDTOConverter.apply(orderItem))
		);
		orderDTO.setOrderItems(orderItemDTOList);

		return orderDTO;
	}

}