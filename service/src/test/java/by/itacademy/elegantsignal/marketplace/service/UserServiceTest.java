package by.itacademy.elegantsignal.marketplace.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;


public class UserServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IUser entity = saveNewUser();

		final IUser entityFromDb = userService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testGetAll() {
		final int initialCount = userService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUser();
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

	@Test
	public void testDelete() {
		final IUser entity = saveNewUser();
		userService.delete(entity.getId());
		assertNull(userService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUser();
		userService.deleteAll();
		assertEquals(0, userService.getAll().size());
	}

	@Test
	public void testGetCount() {
		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUser();
		}
		assertEquals(randomObjectsCount, userService.getAll().size());
	}

	@Test
	public void testUserRole() {
		final IRole role = saveNewRole();
		assertNotNull(role.getId());
		assertNotNull(role.getName());
		final Set<IRole> roleSet = new HashSet<IRole>();
		roleSet.add(role);

		final IUser user = saveNewUser(roleSet);

		final IUser userFromDb = userService.getFullInfo(user.getId());
		final Set<IRole> roleSetFromDb = userFromDb.getRole();

		final IRole firstRoleFromDb = roleSetFromDb.iterator().next();
		assertEquals(role.getName(), firstRoleFromDb.getName());
	}

	@Test
	public void testGetUserByEmail() {
		final Set<IRole> roleSet = new HashSet<IRole>();
		roleSet.add(saveNewRole());

		final IUser userFromRam = saveNewUser(roleSet);

		final IUser userFromDb = userService.getUserByEmail(userFromRam.getEmail());
		final Set<IRole> roleSetFromDb = userFromDb.getRole();

		for (final IRole roleFromRam : userFromRam.getRole()) {
			final IRole firstRoleFromDb = roleSetFromDb.iterator().next();
			assertEquals(roleFromRam.getName(), firstRoleFromDb.getName());
		}
	}

}
