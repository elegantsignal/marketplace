package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;


@Component
public class BookToDTOConverter implements Function<IBook, BookDTO> {

	@Override
	public BookDTO apply(final IBook book) {
		final BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setCover(book.getCover());
		bookDTO.setDescription(book.getDescription());
		bookDTO.setPublished(book.getPublished());
		bookDTO.setPrice(book.getProduct().getPrice());
		bookDTO.setCreated(book.getCreated());
		bookDTO.setUpdated(book.getUpdated());
		return bookDTO;
	}

}
