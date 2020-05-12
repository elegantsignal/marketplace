package by.itacademy.elegantsignal.marketplace.web.controller;

import javax.servlet.http.HttpServletRequest;

import by.itacademy.elegantsignal.marketplace.daoapi.filter.AbstractFilter;
import by.itacademy.elegantsignal.marketplace.web.dto.grid.GridStateDTO;
import by.itacademy.elegantsignal.marketplace.web.dto.SortDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractController {

	final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

	protected GridStateDTO getListDTO(final HttpServletRequest req) {
		final String sessionModelName = getClass().getSimpleName() + "_GRID_STATE";

		GridStateDTO gridState = (GridStateDTO) req.getSession().getAttribute(sessionModelName);
		if (gridState == null) {
			gridState = new GridStateDTO();
			req.getSession().setAttribute(sessionModelName, gridState);
		}
		req.setAttribute(GridStateDTO.GRID_STATE_SESSION_KEY, gridState);
		return gridState;
	}

	protected void prepareFilter(GridStateDTO gridState, AbstractFilter filter) {
		filter.setLimit(gridState.getItemsPerPage());
		int offset = gridState.getItemsPerPage() * (gridState.getPage() - 1);
		filter.setOffset(gridState.getTotalCount() < offset ? 0 : offset);

		final SortDTO sortModel = gridState.getSort();
		if (sortModel != null) {
			filter.setSortColumn(sortModel.getColumn());
			filter.setSortOrder(sortModel.isAscending());
		}
	}

}
