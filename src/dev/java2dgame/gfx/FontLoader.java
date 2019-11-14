package dev.java2dgame.gfx;

import java.awt.Font;
import java.io.InputStream;

public class FontLoader {
	public static Font getFontFromPath(String path) throws Exception {
		InputStream is = FontLoader.class.getResourceAsStream(path);
		Font font = Font.createFont(Font.TRUETYPE_FONT, is);
		return font;
    }
}