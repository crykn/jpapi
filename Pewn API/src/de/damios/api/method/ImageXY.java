package de.damios.api.method;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageXY {
	private ImageXY() {
	}

	public static BufferedImage get(int gameid, String filename, int width,
			int height) {
		try {
			return ImageIO.read(new URL("http://pewn.de/image/projects/" + gameid
					+ "/files/" + filename + "?width=" + width + "&height="
					+ height));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
