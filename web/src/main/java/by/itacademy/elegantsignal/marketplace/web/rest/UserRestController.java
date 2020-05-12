package by.itacademy.elegantsignal.marketplace.web.rest;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import by.itacademy.elegantsignal.marketplace.web.converter.UserFromDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.converter.UserToDTOConverter;
import by.itacademy.elegantsignal.marketplace.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserRestController extends AbstractRestController {

	@Autowired private IUserService service;
	@Autowired private UserToDTOConverter toDtoConverter;
	@Autowired private UserFromDTOConverter fromDtoConverter;

	@GetMapping("/users")
	public List<UserDTO> get() {
		return service.getAll().stream().map(toDtoConverter).collect(Collectors.toList());
	}

	@PostMapping("/users")
	public ResponseEntity<UserDTO> post(@RequestBody final UserDTO newUserDto) {
		final IUser newUser = fromDtoConverter.apply(newUserDto);
		service.save(newUser);
		return new ResponseEntity<UserDTO>(toDtoConverter.apply(newUser), HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Void> put(@PathVariable final Integer id, @RequestBody final UserDTO updatedUserDto) {
		updatedUserDto.setId(id);
		final IUser newUser = fromDtoConverter.apply(updatedUserDto);
		service.save(newUser);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable final Integer id) {
		final IUser user = service.get(id);
		final UserDTO dto = toDtoConverter.apply(user);
		if (dto == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
