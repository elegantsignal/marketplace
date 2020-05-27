package by.itacademy.elegantsignal.marketplace.web.rest;

import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class SearchRestController extends AbstractRestController {

	@Autowired private IBookService bookService;
	@Autowired private BookToDTOConverter bookToDTOConverter;

	@GetMapping("/search/{query}")
	public List<BookDTO> search(@PathVariable final String query) {
		return bookService.search(query).stream().map(bookToDTOConverter).collect(Collectors.toList());
	}
}
