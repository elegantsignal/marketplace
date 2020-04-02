package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.function.Function;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;


@Component
public class BookFromDTOConverter implements Function<BookDTO, IBook> {

	@Autowired
	private IBookService bookService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IUserService userService;

	@Override
	public IBook apply(final BookDTO dto) {
		IBook book;
		try {
			book = bookService.getFullInfo(dto.getId());
		} catch (final NoResultException e) {
			book = bookService.createEntity();
		}

		book.setTitle(dto.getTitle());
		book.setCover(dto.getCover());
		book.setPublished(dto.getPublished());
		book.setDescription(dto.getDescription());

		IProduct product = book.getProduct();
		if (product == null) {
			product = productService.createEntity();
		}
		product.setUser(getHardcodedUser());
		product.setType(ProductType.BOOK);
		product.setPrice(dto.getPrice());
		productService.save(product);
		book.setProduct(product);
//		entity.setGenre(dto.getGenre());
		return book;
	}

	@Deprecated
	private IUser getHardcodedUser() {
		final UserFilter userFilter = new UserFilter();
		userFilter.setEmail("asimov@exampl.com");
		return userService.findOne(userFilter);
	}
}
