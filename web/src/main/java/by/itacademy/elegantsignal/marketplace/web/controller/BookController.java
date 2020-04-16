package by.itacademy.elegantsignal.marketplace.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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


@Controller
@RequestMapping(value = "/book")
public class BookController extends AbstractController {

	public static final String FILE_FOLDER = "/home/binbrayer/projects/elegantsignal/marketplace/media/";

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
		} else {
			if (!file.isEmpty()) {
				String originName = file.getOriginalFilename().trim().toLowerCase();
				String extension = originName.substring(originName.lastIndexOf('.')).trim().toLowerCase();

				String fileName = UUID.randomUUID().toString() + extension;

				InputStream inputStream = file.getInputStream();
				Files.copy(inputStream, new File(FILE_FOLDER + fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);

				formModel.setCover(fileName);
			}
			// save old file path
			Path oldPath = Paths.get(FILE_FOLDER + bookService.get(formModel.getId()).getCover());
			final IBook book = fromDtoConverter.apply(formModel);
			bookService.save(book);

			// delete old file after save
			try {
				Files.delete(oldPath);
			} catch (NoSuchFileException e) {}

			return "redirect:/book";
		}
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

		return new ModelAndView(VIEW_NAME, hashMap);
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
