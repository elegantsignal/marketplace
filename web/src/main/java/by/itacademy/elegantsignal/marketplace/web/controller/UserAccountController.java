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

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUserAccount;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.UserAccountFilter;
import by.itacademy.elegantsignal.marketplace.service.IUserAccountService;
import by.itacademy.elegantsignal.marketplace.web.converter.UserAccountFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.UserAccountToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.GridStateDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.UserAccountDTO;


@Controller
@RequestMapping(value = "/users")
public class UserAccountController extends AbstractController {

	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private UserAccountToDTOConverter toDtoConverter;
	@Autowired
	private UserAccountFromDTOConverter fromDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final UserAccountFilter filter = new UserAccountFilter();

		final List<IUserAccount> entities = userAccountService.find(filter);
		final List<UserAccountDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(userAccountService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		System.out.println(models);
		return new ModelAndView("users.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IUserAccount newEntity = userAccountService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("users.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final UserAccountDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "users.edit";
		} else {
			final IUserAccount entity = fromDtoConverter.apply(formModel);
			userAccountService.save(entity);
			return "redirect:/users";
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		userAccountService.delete(id);
		return "redirect:/users";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IUserAccount dbModel = userAccountService.get(id);
		final UserAccountDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("users.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final UserAccountDTO dto = toDtoConverter.apply(userAccountService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("users.edit", hashMap);
	}

}
