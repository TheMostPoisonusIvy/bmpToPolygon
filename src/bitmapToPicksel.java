package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bitmapToPicksel {
    public int[][] bild;
    BufferedImage bfr;

    public bitmapToPicksel(String s) {
        try {
            bfr = ImageIO.read(new File(s));
            bild = new int[bfr.getWidth()][bfr.getHeight()];
            for (int x = 0; x < bfr.getWidth(); x++) {
                for (int y = 0; y < bfr.getHeight(); y++) {
                    bild[x][y] = bfr.getRGB(x, y);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not get img");
        }
    }
}
