package de.damios.jpapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.damios.jpapi.model.Hashtag;
import de.damios.jpapi.model.Project;

public class HashtagTest {

	@Test
	public void test() throws IOException {
		Project p = Project.getByProjectId(8568);

		assertTrue(p.getHashtags().length > 0);

		List<Hashtag> tags = Arrays.asList(Hashtag.getByProjectId(8568));
		for (Hashtag h : p.getHashtags()) {
			if (!tags.contains(h))
				fail("Hashtag " + h.getName() + " not found!");
		}

		for (Hashtag h : p.getHashtags()) {
			if (h.getMetatag() != null) { // has metatag
				assertEquals(h.getMetatag().getName(), h.getTag());
			}

			assertTrue(h.getUsageCount() >= 1);
		}
	}

}
