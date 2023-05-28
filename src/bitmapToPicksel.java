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
            bild = new int[bfr.getHeight()][bfr.getWidth()];
            // y-dimension
            for (int x = 0; x < bfr.getHeight(); x++) {
                // x-dimension
                for (int y = 0; y < bfr.getWidth(); y++) {
                    bild[x][y] = bfr.getRGB(x, y);
                }
            }

            for (int x = 0; x < bfr.getHeight(); x++) {
                // x-dimension
                for (int y = 0; y < bfr.getWidth(); y++) {
                    System.out.print(bild[x][y]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Could not get img");
        }
    }
}
