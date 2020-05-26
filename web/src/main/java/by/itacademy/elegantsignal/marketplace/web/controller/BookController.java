package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.web.converter.BookFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.BookToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.BookDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.grid.GridStateDTO;
import by.itacademy.elegantsignal.marketplace.web.security.ExtendedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/book")
public class BookController extends AbstractController {

	private static final String FORM_MODEL = "formModel";
	private static final String VIEW_NAME = "book.edit";

	@Autowired private IBookService bookService;
	@Autowired private IProductService productService;
	@Autowired private IGenreService genreService;
	@Autowired private BookToDTOConverter toDtoConverter;
	@Autowired private BookFromDTOConverter fromDtoConverter;

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
	public ModelAndView showForm(final ExtendedToken token) {
		final Map<String, Object> hashMap = new HashMap<>();
		final IBook book = bookService.createBook(token.getId());
		hashMap.put(FORM_MODEL, toDtoConverter.apply(book));

		return new ModelAndView(VIEW_NAME, hashMap);
	}

	@PostMapping
	public String save(
		@Valid @ModelAttribute(FORM_MODEL) final BookDTO formModel,
		final ExtendedToken token,
		@RequestParam(name = "cover", required = false) final MultipartFile coverFile,
		@RequestParam(name = "pdf", required = false) final MultipartFile pdfFile,
		final BindingResult result) throws IOException {

		if (result.hasErrors()) {
			return VIEW_NAME;
		}

		if (formModel.getId() != null) {
			final Integer ownerId = productService.getFullInfo(formModel.getId()).getUser().getId();
			if (!token.getId().equals(ownerId)) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			}
		}

		final IBook book = fromDtoConverter.apply(formModel);

		final Map<String, InputStream> bookFiles = new HashMap<>();
		if (coverFile != null && !coverFile.isEmpty()) {
			bookFiles.put("cover", coverFile.getInputStream());
		}
		if (pdfFile != null && !pdfFile.isEmpty()) {
			bookFiles.put("pdf", pdfFile.getInputStream());
		}

		bookService.save(book, bookFiles, formModel.getPrice(), token.getId());
		return "redirect:/book/" + book.getId() + "/edit";

	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") final Integer id, final ExtendedToken token) {
		final IBook book = bookService.getFullInfo(id);
		if (!token.getId().equals(book.getProduct().getUser().getId())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		bookService.delete(id);
		return "redirect:/book";
	}

	@GetMapping("/{id}")
	public ModelAndView viewDetails(@PathVariable(name = "id") final Integer id) {
		final IBook book = bookService.getFullInfo(id);
		final BookDTO bookDto = toDtoConverter.apply(book);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put(FORM_MODEL, bookDto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);

		return new ModelAndView("book.item", hashMap);
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable(name = "id") final Integer id, final ExtendedToken token) {
		final IBook book = bookService.getFullInfo(id);

		if (!token.getId().equals(book.getProduct().getUser().getId())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		final BookDTO bookDto = toDtoConverter.apply(book);

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
