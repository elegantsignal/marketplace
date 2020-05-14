package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IOrderItem;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class RestrictedAssetsServiceTest extends AbstractTest {

	@Autowired protected IRestrictedAssetsService restrictedAssetsService;

	@Test
	public void testGetBookPdfByToken() {
		final IProduct product = saveNewProduct(productService.createEntity());
		final IBook book = saveNewBook(bookService.createEntity().setProduct(product));
		final IOrderItem orderItem = saveNewOrderItem(orderItemService.createEntity().setProduct(product));
		final IDownload download = saveNewDownload(downloadService.createEntity().setOrderItem(orderItem));

		final File pdf = restrictedAssetsService.getBookPdfByToken(download.getToken());
		assertNotNull(pdf);
	}

}
