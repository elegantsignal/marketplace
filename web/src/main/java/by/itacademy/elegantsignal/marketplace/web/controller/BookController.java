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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.GridStateDTO;


@Controller
@RequestMapping(value = "/book")
public class BookController extends AbstractController {

	@Autowired
	private IBookService bookService;

	@Autowired
	private BookToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final BookFilter filter = new BookFilter();
		prepareFilter(gridState, filter);

		final List<IBook> entities = bookService.find(filter);
		final List<BookDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(bookService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("book.list", models);
	}
}
