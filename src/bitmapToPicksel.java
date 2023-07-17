package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class bitmapToPicksel {
    public int[][] bild;
    public picksel[][] pickselBild;
    public int height, width;
    BufferedImage bfr;
    pickselToPolygon p;

    public bitmapToPicksel(String s) {
        try {
            this.bfr = ImageIO.read(new File(s));
            this.bild = new int[bfr.getWidth()][bfr.getHeight()];
            this.height = bfr.getHeight();
            this.width = bfr.getWidth();
            for (int x = 0; x < bfr.getWidth(); x++) {
                for (int y = 0; y < bfr.getHeight(); y++) {
                    this.bild[x][y] = bfr.getRGB(x, y);
                }
            }
            this.pickselBild = new picksel[width][height];
            for (int x = 0; x < bfr.getWidth(); x++) {
                for (int y = 0; y < bfr.getHeight(); y++) {
                    this.pickselBild[x][y] = new picksel(x, y, this.bild[x][y]);
                }
            }
            p = new pickselToPolygon(this);
        } catch (IOException e) {
            System.out.println("Could not get img");
        }
    }
}
