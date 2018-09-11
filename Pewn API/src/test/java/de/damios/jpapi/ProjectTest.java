package de.damios.jpapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import de.damios.jpapi.model.Project;
import de.damios.jpapi.model.Project.OrderedBy;

public class ProjectTest {

	@Test
	public void test() throws IOException {
		int allRatingCount = Project.getAll(OrderedBy.RATING).length;

		assertEquals(allRatingCount,
				Project.getAll(OrderedBy.CREATION_DATE).length);
		assertEquals(allRatingCount,
				Project.getAll(OrderedBy.UPDATE_DATE).length);

		Project p = Project.getByProjectId(8568);
		assertEquals("Shooter Reloaded", p.getName());
		assertEquals("damios", p.getAuthor().getName());
		assertEquals("2014-08-09 14:45:50.0", p.getCreationDate().toString());
		assertEquals(8568, p.getId());
		assertEquals(Project.getLatest(),
				Project.getAll(OrderedBy.CREATION_DATE)[0]);

		Project rand = Project.getRandom();
		assertNotNull(rand.getAuthor());
		assertNotNull(rand.getName());
	}

}
