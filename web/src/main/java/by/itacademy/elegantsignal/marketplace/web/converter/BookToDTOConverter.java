package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.LazyInitializationException;
import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;


@Component
public class BookToDTOConverter implements Function<IBook, BookDTO> {

	@Override
	public BookDTO apply(final IBook book) {
		final BookDTO bookDto = new BookDTO();
		bookDto.setId(book.getId());
		bookDto.setTitle(book.getTitle());
		bookDto.setAuthor(book.getAuthor());
		bookDto.setCover(book.getCover());
		bookDto.setPdf(book.getPdf());
		bookDto.setDescription(book.getDescription());
		bookDto.setPublished(book.getPublished());
		bookDto.setPrice(book.getProduct().getPrice());
		bookDto.setCreated(book.getCreated());
		bookDto.setUpdated(book.getUpdated());


		try {
			final Set<IGenre> genreSet = book.getGenre();
			if (!genreSet.isEmpty()) {
				bookDto.setGenreIds(genreSet.stream().map(IGenre::getId).collect(Collectors.toSet()));
			}
		} catch (final LazyInitializationException e) {

		}

		return bookDto;
	}

}
