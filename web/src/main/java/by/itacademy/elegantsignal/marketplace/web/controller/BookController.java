package by.itacademy.elegantsignal.marketplace.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;


@Controller
@RequestMapping(value = "/book")
public class BookController {
	private final IBookService bookService;
	private final BookToDTOConverter toDtoConverter;

	@Autowired
	private BookController(final IBookService bookService, final BookToDTOConverter toDtoConverter) {
		super();
		this.bookService = bookService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {
		final BookFilter filter = new BookFilter();
		final List<IBook> entities = bookService.find(filter);
		final List<BookDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		final Map<String, Object> models = new HashMap<>();

		models.put("gridItems", dtos);
		return new ModelAndView("book.list", models);
	}
}
