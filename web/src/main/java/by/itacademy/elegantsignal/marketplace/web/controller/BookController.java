package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.BookToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.GridStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/book")
public class BookController extends AbstractController {

	private static final String FORM_MODEL = "formModel";
	private static final String VIEW_NAME = "book.edit";

	@Autowired
	private IBookService bookService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IGenreService genreService;

	@Autowired
	private BookToDTOConverter toDtoConverter;

	@Autowired
	private BookFromDTOConverter fromDtoConverter;

	@GetMapping()
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

	@GetMapping(value = "/add")
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IProduct product = productService.createEntity();
		final IBook book = bookService.createEntity();
		book.setProduct(product);
		hashMap.put(FORM_MODEL, toDtoConverter.apply(book));

		return new ModelAndView(VIEW_NAME, hashMap);
	}

	@PostMapping()
	public String save(
		@Valid @ModelAttribute(FORM_MODEL) final BookDTO formModel,
		@RequestParam("cover") final MultipartFile file,
		final BindingResult result) throws IOException {

		if (result.hasErrors()) {
			return VIEW_NAME;
		}

		final IBook book = fromDtoConverter.apply(formModel);

		if (!file.isEmpty()) {
			bookService.save(book, file.getInputStream());
		} else {
			bookService.save(book);
		}

		return "redirect:/book";

	}

	@GetMapping(value = "/{id}/delete")
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		bookService.delete(id);
		return "redirect:/book";
	}

	@GetMapping(value = "/{id}")
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IBook book = bookService.getFullInfo(id);
		final BookDTO bookDto = toDtoConverter.apply(book);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put(FORM_MODEL, bookDto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);

		return new ModelAndView("book.item", hashMap);
	}

	@GetMapping(value = "/{id}/edit")
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final BookDTO bookDto = toDtoConverter.apply(bookService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put(FORM_MODEL, bookDto);
		loadCommonFormModels(hashMap);

		return new ModelAndView(VIEW_NAME, hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final Map<Integer, String> genreMap = genreService.getAll()
			.stream()
			.collect(Collectors.toMap(IGenre::getId, IGenre::getName));
		hashMap.put("genreChoices", genreMap);
	}
}
