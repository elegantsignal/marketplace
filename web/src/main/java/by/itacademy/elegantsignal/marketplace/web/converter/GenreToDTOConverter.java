package by.itacademy.elegantsignal.marketplace.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IGenre;
import by.itacademy.elegantsignal.marketplace.web.dto.GenreDTO;


@Component
public class GenreToDTOConverter implements Function<IGenre, GenreDTO> {

	@Override
	public GenreDTO apply(final IGenre entity) {
		final GenreDTO dto = new GenreDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

}
