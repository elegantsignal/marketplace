package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.service.IGenreService;
import by.itacademy.elegantsignal.marketplace.web.dto.GenreDTO;


@Component
public class GenreFromDTOConverter implements Function<GenreDTO, IGenre> {

	@Autowired
	private IGenreService genreService;

	@Override
	public IGenre apply(final GenreDTO dto) {
		final IGenre entity = genreService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
