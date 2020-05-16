package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.OrderStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrder;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PaymentServiceTest extends AbstractTest {

	@Test
	void testSuccessfulPayment(){
		final IUser user = saveNewUser(userService.createEntity());

		final IProduct product = saveNewProduct(productService.createEntity().setUser(user));

		final IBook book = saveNewBook(bookService.createEntity().setProduct(product));

		final IOrder order = saveNewOrder(orderService.createEntity().setUser(user).setStatus(OrderStatus.CART));

		final IOrderItem orderItem = saveNewOrderItem(orderItemService.createEntity().setProduct(product).setOrder(order));

		final IOrder cart = cartService.getCartByUserId(user.getId());

		paymentService.checkout(cart);

		List<IOrder> orderList = orderService.getOrdersByUserId(user.getId());
		orderList.forEach(payedOrder ->{
			payedOrder.getOrderItems().forEach(payedOrderItem ->{
				List<IDownload> downloadList = payedOrderItem.getDownloadList();
				assertFalse(downloadList.isEmpty());
			});
		});

		final List<IDownload> downloadFromDbList = downloadService.getDownloadsByUserId(user.getId());
		downloadFromDbList.forEach(downloadItem -> {
			assertNotNull(downloadItem.getToken());
			assertNotNull(downloadItem.getOrderItem().getOrder().getUser().getId());
			assertNotNull(downloadItem.getOrderItem().getProduct().getBook().getPdf());
		});
	}
}
