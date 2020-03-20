package by.itacademy.elegantsignal.marketplace.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.GenreFilter;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.web.converter.GenreFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.GenreToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.GenreDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.GridStateDTO;


@Controller
@RequestMapping(value = "/genre")
public class GenreController extends AbstractController {

	@Autowired
	private IGenreService genreService;
	@Autowired
	private GenreToDTOConverter toDtoConverter;
	@Autowired
	private GenreFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final GenreFilter filter = new GenreFilter();
		prepareFilter(gridState, filter);

		final List<IGenre> entities = genreService.find(filter);
		final List<GenreDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(genreService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("genre.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IGenre newEntity = genreService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("genre.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final GenreDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "genre.edit";
		} else {
			final IGenre entity = fromDtoConverter.apply(formModel);
			genreService.save(entity);
			return "redirect:/genre";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		genreService.delete(id);
		return "redirect:/genre";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IGenre dbModel = genreService.get(id);
		final GenreDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("genre.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final GenreDTO dto = toDtoConverter.apply(genreService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("genre.edit", hashMap);
	}

}
