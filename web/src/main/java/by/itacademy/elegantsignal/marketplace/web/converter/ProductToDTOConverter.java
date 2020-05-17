package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.web.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class ProductToDTOConverter implements Function<IProduct, ProductDTO> {

	@Autowired BookToDTOConverter bookToDTOConverter;

	@Override
	public ProductDTO apply(final IProduct product) {
		final ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setType(product.getType().toString());
		productDTO.setPrice(product.getPrice());
		if (product.getType() == ProductType.BOOK) {
			productDTO.setBook(bookToDTOConverter.apply(product.getBook()));
		}
		return productDTO;
	}

}
