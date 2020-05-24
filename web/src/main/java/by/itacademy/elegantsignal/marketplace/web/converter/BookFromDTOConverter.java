package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.filestorage.FileUtils;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class BookFromDTOConverter implements Function<BookDTO, IBook> {

	@Autowired private FileUtils fileUtils;
	@Autowired private IBookService bookService;
	@Autowired private IProductService productService;
	@Autowired private IGenreService genreService;

	@Override
	public IBook apply(final BookDTO dto) {
		IBook book;
		try {
			book = bookService.getFullInfo(dto.getId());
		} catch (final NoResultException e) {
			book = bookService.createEntity();
		}

		book.setTitle(dto.getTitle());
		book.setCover(fileUtils.getAbsolutePath(dto.getCover()));
		book.setPdf(fileUtils.getAbsolutePath(dto.getPdf()));
		book.setPublished(dto.getPublished());
		book.setDescription(dto.getDescription());

		// product
//		IProduct product = book.getProduct();
//		if (product == null) {
//			product = productService.createEntity();
//		}
//		product.setType(ProductType.BOOK);
//		product.setPrice(dto.getPrice());
//		productService.save(product);
//		book.setProduct(product);

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
}
