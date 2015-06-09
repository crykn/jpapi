package test;

import de.damios.api.object.Project;

public class Test {
	public static void main(String[] args) {
		System.out.println(Project.get("damios")[0].author.username);
		System.out.println(Project.get("damios")[0].downloadWindows.getHost());

		// System.out.println(Project.getAll(OrderedBy.RATING)[0].author.level);

		// Set<String> tmp = Project.get("damios")[0].getHashtags();
		// for(String s : tmp){
		// System.out.println(s);
		// }

		// try {
		// ImageIO.write(ImageXY.get(2759, "ogam2013-screenshots.png", 950,
		// 534), "jpg", new File("image.jpg"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}
}
