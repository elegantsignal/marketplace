package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
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
	private IGenreService genreService;

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
		// TODO make decision what to do with cover field
//		book.setCover(dto.getCover());
		book.setPublished(dto.getPublished());
		book.setDescription(dto.getDescription());

		// product
		IProduct product = book.getProduct();
		if (product == null) {
			product = productService.createEntity();
		}
		product.setUser(getHardcodedUser());
		product.setType(ProductType.BOOK);
		product.setPrice(dto.getPrice());
		productService.save(product);
		book.setProduct(product);

		// genre
		final Set<Integer> genreIds = dto.getGenreIds();
		if (CollectionUtils.isNotEmpty(genreIds)) {
			book.setGenre(genreIds.stream().map(id -> {
				final IGenre genre = genreService.createEntity();
				genre.setId(id);
				return genre;
			}).collect(Collectors.toSet()));
		}

		return book;
	}

	@Deprecated
	private IUser getHardcodedUser() {
		final UserFilter userFilter = new UserFilter();
		userFilter.setEmail("asimov@exampl.com");
		return userService.findOne(userFilter);
	}
}
