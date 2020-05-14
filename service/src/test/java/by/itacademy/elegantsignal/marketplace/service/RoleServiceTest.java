package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IRole;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class RoleServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IRole role = saveNewRole();

		final IRole roleFromDb = roleService.get(role.getId());

		assertNotNull(roleFromDb);
		assertNotNull(roleFromDb.getId());
		assertEquals(role.getName(), roleFromDb.getName());
	}

	@Test
	public void testGetAll() {
		final int initialCount = roleService.getAll().size();

		for (int i = 0; i < initialCount; i++) {
			saveNewRole();
		}

		final List<IRole> allRoles = roleService.getAll();

		for (final IRole roleFromDb : allRoles) {
			assertNotNull(roleFromDb.getId());
		}

		assertEquals(initialCount, allRoles.size());
	}

	@Test
	public void testDelete() {
		final IRole role = saveNewRole();
		roleService.delete(role.getId());
		assertNull(roleService.get(role.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewRole();
		roleService.deleteAll();
		assertEquals(0, roleService.getAll().size());
	}

}
