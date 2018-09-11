package de.damios.jpapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import de.damios.jpapi.model.Project;
import de.damios.jpapi.model.User;

public class UserTest {

	@Test
	public void test() throws IOException {
		Project rand = Project.getRandom();

		User dev = rand.getAuthor();
		assertTrue(Arrays.asList(dev.getProjects()).contains(rand));
		assertNotNull(dev.getName());
		assertNotNull(dev.getProfile());
		assertTrue(dev.getLevel() >= 0);

		assertEquals(dev, User.getByUserId(dev.getId()));
		assertEquals(dev, User.getByUsername(dev.getName()));
	}

}