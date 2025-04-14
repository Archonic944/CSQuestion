package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     * Load a 1-depth BMP file
     * @param path path to file
     * @return 2D array of booleans, true for black, false for white (or whatever other  the bmp is using)
     */
    public static boolean[][] loadBMP(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();
            boolean[][] result = new boolean[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = image.getRGB(x, y);
                    result[y][x] = (color & 0xFFFFFF) == 0; // true for black, false for other colors
                }
            }

            return result;
        }  catch (IOException e) {
            throw new RuntimeException("Error loading BMP file: " + e.getMessage(), e);
        }
    }
}
