package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserServiceTest extends AbstractTest {

	@Test public void testCreate() {
		final IUser user = saveNewUser(userService.createEntity());

		final IUser entityFromDb = userService.get(user.getId());

		assertNotNull(entityFromDb);
		assertEquals(user.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entityFromDb.getCreated(), entityFromDb.getUpdated());

		final IUser newUser = userService.createEntity().setName("alice").setEmail(user.getEmail()).setPassword("1234567890");
		assertThrows(PersistenceException.class, () -> userService.save(newUser));
	}

	@Test public void testGetAll() {
		final int initialCount = userService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUser(userService.createEntity());
		}

		final List<IUser> allEntities = userService.getAll();

		for (final IUser entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + initialCount, allEntities.size());
	}

	@Test public void testDelete() {
		final IUser entity = saveNewUser(userService.createEntity());
		userService.delete(entity.getId());
		assertNull(userService.get(entity.getId()));
	}

	@Test public void testDeleteAll() {
		saveNewUser(userService.createEntity());
		userService.deleteAll();
		assertEquals(0, userService.getAll().size());
	}

	@Test public void testGetCount() {
		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUser(userService.createEntity());
		}
		assertEquals(randomObjectsCount, userService.getAll().size());
	}

	@Test public void testUserRole() {
		final IRole role = saveNewRole();
		assertNotNull(role.getId());
		assertNotNull(role.getName());
		final Set<IRole> roleSet = new HashSet<>();
		roleSet.add(role);

		final IUser user = saveNewUser(userService.createEntity().setRole(roleSet));

		final IUser userFromDb = userService.getFullInfo(user.getId());
		final Set<IRole> roleSetFromDb = userFromDb.getRole();

		final IRole firstRoleFromDb = roleSetFromDb.iterator().next();
		assertEquals(role.getName(), firstRoleFromDb.getName());
	}

	@Test public void testGetUserByEmail() {
		final Set<IRole> roleSet = new HashSet<>();
		roleSet.add(saveNewRole());

		final IUser userFromRam = saveNewUser(userService.createEntity().setRole(roleSet));

		final IUser userFromDb = userService.getUserByEmail(userFromRam.getEmail());
		final Set<IRole> roleSetFromDb = userFromDb.getRole();

		for (final IRole roleFromRam : userFromRam.getRole()) {
			final IRole firstRoleFromDb = roleSetFromDb.iterator().next();
			assertEquals(roleFromRam.getName(), firstRoleFromDb.getName());
		}
	}

}
