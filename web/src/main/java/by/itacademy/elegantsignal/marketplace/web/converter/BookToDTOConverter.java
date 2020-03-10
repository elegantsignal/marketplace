package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;

@Component
public class BookToDTOConverter implements Function<IBook, BookDTO> {

	@Override
	public BookDTO apply(final IBook entity) {
		final BookDTO dto = new BookDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
