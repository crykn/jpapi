package example;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import de.damios.jpapi.method.ImageXY;
import de.damios.jpapi.object.Project;
import de.damios.jpapi.object.Project.OrderedBy;

/**
 * Demonstriert einige Funktionen des Wrappers
 * 
 * @author damios
 * @version 1.0
 *
 */
public class Example1 {
	public static void main(String[] args) {
		System.out.println(Project.get("damios")[0].author.username);
		System.out.println(Project.get("damios")[0].downloadWindows.getHost());
		System.out.println(Project.get("damios")[0].lastUpdateDate);

		System.out.println(Project.getAll(OrderedBy.RATING)[0].author.level);

		Set<String> tmp = Project.get("damios")[0].getHashtags();
		for (String s : tmp) {
			System.out.println(s);
		}

		try {
			ImageIO.write(
					ImageXY.get(2759, "ogam2013-screenshots.png", 950, 534),
					"jpg", new File("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
