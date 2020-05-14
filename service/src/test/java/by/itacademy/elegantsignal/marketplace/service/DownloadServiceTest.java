package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DownloadServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IDownload download = saveNewDownload(downloadService.createEntity());
		final IDownload downloadFromDb = downloadService.get(download.getId());
		assertNotNull(downloadFromDb);
		assertNotNull(download.getOrderItem());
		assertNotNull(download.getToken());
		assertEquals(download.getId(), downloadFromDb.getId());
		assertEquals(download.getToken(), downloadFromDb.getToken());
	}

	@Test
	public void testGetDownloadsByUser() {

		final IProduct product = saveNewProduct(productService.createEntity());
		final IBook book = saveNewBook(bookService.createEntity().setProduct(product));
		final IOrderItem orderItem = saveNewOrderItem(orderItemService.createEntity().setProduct(product));
		final IDownload download = saveNewDownload(downloadService.createEntity().setOrderItem(orderItem));

		final Integer memUserId = download.getOrderItem().getOrder().getUser().getId();

		final List<IDownload> downloadFromDbList = downloadService.getDownloadsByUserId(memUserId);
		downloadFromDbList.forEach(x -> System.out.println("test"));
		downloadFromDbList.forEach(downloadItem -> {
			assertNotNull(downloadItem.getToken());
			assertNotNull(downloadItem.getOrderItem().getOrder().getUser().getId());
			assertNotNull(downloadItem.getOrderItem().getProduct().getBook());
		});
	}

}
