package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.grid.GridStateDTO;
import by.itacademy.elegantsignal.marketplace.web.jndi.SMTPCredentials;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller @RequestMapping("/") @Slf4j
public class HomePageController extends AbstractController {

	@Autowired private IBookService bookService;
	@Autowired private BookToDTOConverter bookToDTOConverter;
	@Autowired private SMTPCredentials smtpCredentials;

	@PostConstruct private void init() {
		Validate.notEmpty(smtpCredentials.getEmail());
		log.info("email from custom JNDI resource:{}", smtpCredentials.getEmail());
	}

	@GetMapping
	public ModelAndView index(final HttpServletRequest request,
		@RequestParam(name = "page", required = false) final Integer pageNumber,
		@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(request)
			.setPage(pageNumber)
			.setSort(sortColumn, "id");

		final BookFilter bookFilter = new BookFilter();
		prepareFilter(gridState, bookFilter);

		final List<IBook> bookList = bookService.find(bookFilter);
		final List<BookDTO> bookDTOList = bookList.stream().map(bookToDTOConverter).collect(Collectors.toList());
		gridState.setTotalCount(bookService.getCount(bookFilter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", bookDTOList);
		return new ModelAndView("home", models);
	}

}